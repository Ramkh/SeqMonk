package uk.ac.babraham.SeqMonk.Displays.TsneDataStorePlot;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import uk.ac.babraham.SeqMonk.SeqMonkApplication;
import uk.ac.babraham.SeqMonk.DataTypes.DataStore;
import uk.ac.babraham.SeqMonk.DataTypes.Probes.ProbeList;
import uk.ac.babraham.SeqMonk.Utilities.NumberKeyListener;

/**
 * Sets the options for a t-sne plot of multiple data stores
 */

public class TsneOptionsDialog extends JDialog implements ActionListener {

	private JTextField perplexity;
	private JTextField iterations;
	private ProbeList probes;
	private DataStore [] stores;
	
	public TsneOptionsDialog (ProbeList probes, DataStore [] stores) {
		
		super(SeqMonkApplication.getInstance(),"Tsne Options");
		
		
		this.probes = probes;
		this.stores = stores;
		
		getContentPane().setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=1;
		gbc.gridy=1;
		gbc.weightx=0.5;
		gbc.weighty=0.5;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		getContentPane().add(new JLabel("Perplexity"), gbc);
		
		gbc.gridx++;
		int defaultPerplexity = stores.length/5;
		
		if (defaultPerplexity < 2) defaultPerplexity = 2;
		if (defaultPerplexity > 50) defaultPerplexity = 50;
		
		perplexity = new JTextField(""+defaultPerplexity);
		perplexity.addKeyListener(new NumberKeyListener(false, false));
		
		getContentPane().add(perplexity, gbc);
		
		gbc.gridx=1;
		gbc.gridy++;
		
		getContentPane().add(new JLabel("Max Iterations"), gbc);
		
		gbc.gridx++;
		iterations = new JTextField("1000");
		iterations.addKeyListener(new NumberKeyListener(false, false));
		
		getContentPane().add(iterations, gbc);
		
		
		gbc.gridx=1;
		gbc.gridy++;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		
		JButton submitButton = new JButton("Run Tsne");
		submitButton.addActionListener(this);
		
		getContentPane().add(submitButton, gbc);
		
		setSize(300,150);
		setLocationRelativeTo(SeqMonkApplication.getInstance());
		
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		int perplexityValue = 0;
		int iterationValue = 0;
		
		if (perplexity.getText().length()>0) {
			int tempPerplexity = Integer.parseInt(perplexity.getText());
			if (tempPerplexity>0) perplexityValue = tempPerplexity;
		}
		

		if (iterations.getText().length()>0) {
			int tempIterations = Integer.parseInt(iterations.getText());
			if (tempIterations>0) iterationValue = tempIterations;
		}

		setVisible(false);
		
		new TsneDataStoreResult(probes, stores, iterationValue, perplexityValue);
		
		dispose();
		
	}
	
}
