/**
 * Copyright 2009-17 Simon Andrews
 *
 *    This file is part of SeqMonk.
 *
 *    SeqMonk is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    SeqMonk is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with SeqMonk; if not, write to the Free Software
 *    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package uk.ac.babraham.SeqMonk.Displays.QuantitationTrendPlot;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import uk.ac.babraham.SeqMonk.SeqMonkApplication;
import uk.ac.babraham.SeqMonk.DataTypes.DataCollection;
import uk.ac.babraham.SeqMonk.DataTypes.DataStore;
import uk.ac.babraham.SeqMonk.DataTypes.Probes.ProbeList;

/**
 * The Class TrendOverProbePreferencesDialog sets the preferences from which a
 * trend plot can be drawn.
 */
public class QuantiationTrendPlotPreferencesDialog extends JDialog implements ActionListener {
	
	/** The probes. */
	private ProbeList probes;
	
	/** The stores. */
	private DataStore [] stores;
	
	private QuantitationTrendPlotPreferencesPanel prefPanel;
	
	/** The data collection the features will come from **/
	private DataCollection collection;
	
	/**
	 * Instantiates a new trend over probe preferences dialog.
	 * 
	 * @param probes the probes
	 * @param stores the stores
	 */
	public QuantiationTrendPlotPreferencesDialog (DataCollection collection, ProbeList probes, DataStore [] stores) {
		super(SeqMonkApplication.getInstance(),"Quantitation Trend Preferences");
		this.probes = probes;
		this.stores = stores;
		this.collection = collection;
		
		setupDialog();
	}
	
	
	
	private void setupDialog () {
		
		getContentPane().setLayout(new BorderLayout());
		
		prefPanel = new QuantitationTrendPlotPreferencesPanel(collection);
			
		getContentPane().add(prefPanel,BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		cancelButton.setActionCommand("cancel");
		buttonPanel.add(cancelButton);
		
		JButton plotButton = new JButton("Create Plot");
		plotButton.addActionListener(this);
		plotButton.setActionCommand("plot");
		buttonPanel.add(plotButton);
		
		getContentPane().add(buttonPanel,BorderLayout.SOUTH);
		
		setSize(600,300);
		setLocationRelativeTo(SeqMonkApplication.getInstance());
		setVisible(true);
			
	}
	

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("cancel")) {
			setVisible(false);
			dispose();
		}
		else if (e.getActionCommand().equals("plot")) {
			new QuantitationTrendPlotDialog(probes,stores,prefPanel);
			setVisible(false);
			//dispose(); // Can we do this?
		}
	}

}
