import java.awt.Color;  //Import Libraries
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicArrowButton;

public class Main { //Set initial variable values
	private static int period = 1;
	private static String periodString = "Top 1";
	private static int top = 1;
	private static int bottom = 0;
	private static int hscore = 0;
	private static int vscore = 0;
	private static int outs = 0;
	private static int balls = 0;
	private static int strike = 0;
	private static int firstBase = 0;
	private static int secondBase = 0;
	private static int thirdBase = 0;
	private static ArrayList<Integer> hnumPitches = new ArrayList<Integer>();
	private static ArrayList<String> hPlayers = new ArrayList<String>();
	private static ArrayList<Integer> hMake = new ArrayList<Integer>();
	private static ArrayList<Integer> hMiss = new ArrayList<Integer>();
	private static ArrayList<Integer> vnumPitches = new ArrayList<Integer>();
	private static ArrayList<String> vPlayers = new ArrayList<String>();
	private static ArrayList<Integer> vMake = new ArrayList<Integer>();
	private static ArrayList<Integer> vMiss = new ArrayList<Integer>();
	private static int numPitches = 0;
	private static int hBatter = 0;
	private static int vBatter = 0;
	private static int hPitcher = 0;
	private static int vPitcher = 0;
	private static String Pitcher = "name";
	private static String Batter = "name";
	private static String nextBatter = "name";
	private static int make = 0;
	private static int miss = 0;
	private static int makeNext = 0;
	private static int missNext = 0;
	public static String csvFilePath;
	public static String jsonFilePath;
	public static ArrayList<String> inputs = new ArrayList<String>();
	public static JLabel periodLabel = new JLabel ("Inning: Top 1");
	public static JLabel hscoreLabel = new JLabel ("Score: 0");
	public static JLabel vscoreLabel = new JLabel ("Score: 0");
	public static JLabel hPitcherBatter = new JLabel ("Pitcher:");
	public static JLabel vPitcherBatter = new JLabel ("Batter:");
	public static JTextField enterNumPitches = new JTextField ("0");
	public static JToggleButton finalT = new JToggleButton ("Final");
	public static JToggleButton freeze = new JToggleButton ("Freeze");
	public static boolean freezeVar = false;
	public static JTextField filePath = new JTextField ("C:/Users/LiveStream/Desktop/scoreboard.json");
	public static JLabel strikesLabel = new JLabel ("Strikes: 0");
	public static JLabel outsLabel = new JLabel ("Outs: 0");
	public static JLabel ballsLabel = new JLabel ("Balls: 0");
	public static boolean isPitcherHome = true;
	public static JToggleButton firstBaseButton = new JToggleButton ("1st Base");
	public static JToggleButton secondBaseButton = new JToggleButton ("2nd Base");
	public static JToggleButton thirdBaseButton = new JToggleButton ("3rd Base");
	public static JTextField BatterHits = new JTextField ("0");
	public static JTextField BatterAtBats = new JTextField ("0");
	
	public static void main (String [] args) {
		//Create an image to use as the logo
		ImageIcon icon = new ImageIcon("JCD LIVE LOGO 1 .png");
		Image logo = icon.getImage();
		
		//Create a JFrame to Select a File location
		JFrame selectFile = new JFrame ();
		selectFile.setTitle("Scoreboard Controller: Select File");
		selectFile.setSize(500, 250);
		selectFile.setIconImage(logo);
		selectFile.setLayout(null);
		selectFile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Create Components for the Select File Frame
		JLabel storageInstructions = new JLabel ("Please Enter the File path of where the .csv storage file is located:");
		storageInstructions.setBounds(20, 20, 500, 15);
		selectFile.add(storageInstructions);
		JTextField storageFilePath = new JTextField ("C:/Users/LiveStream/Desktop/roster.csv");
		storageFilePath.setBounds(20, 40, 300, 20);
		selectFile.add(storageFilePath);
		JButton storageBrowse = new JButton ("Browse");
		storageBrowse.setBounds(340, 40, 100, 20);
		selectFile.add(storageBrowse);
		
		//Create Components for the Select File Frame
		JLabel instructions = new JLabel ("Please Enter the File path of where the .json file should be located:");
		instructions.setBounds(20, 65, 500, 15);
		selectFile.add(instructions);
		JTextField filePath = new JTextField ("C:/Users/LiveStream/Desktop/scoreboard.json");
		filePath.setBounds(20, 85, 300, 20);
		selectFile.add(filePath);
		JButton browse = new JButton ("Browse");
		browse.setBounds(340, 85, 100, 20);
		selectFile.add(browse);
		JButton enter = new JButton ("Go to Scoreboard Controller");
		enter.setBounds(125, 130, 250, 20);
		selectFile.add(enter);
		JFileChooser chooser = new JFileChooser();
		selectFile.setVisible(true);
		
		//Create a JFrame for Controller
		JFrame frame = new JFrame();
		frame.setTitle("Scoreboard Controller");
		frame.setSize(800, 650);
		frame.setLayout(null);
		frame.setIconImage(logo);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		periodLabel.setBounds(10,10,100, 20);
		frame.add(periodLabel);
		
		JButton periodMinus = new JButton ("-");
		periodMinus.setBounds(100, 10, 50, 20);
		frame.add(periodMinus);

		JButton periodPlus = new JButton ("+");
		periodPlus.setBounds(150, 10, 50, 20);
		frame.add(periodPlus);

		strikesLabel.setBounds(350, 475, 100, 20);
		frame.add(strikesLabel);

		ballsLabel.setBounds(500, 475, 100, 20);
		frame.add(ballsLabel);

		outsLabel.setBounds(650, 475, 100, 20);
		frame.add(outsLabel);

		JButton strikePlus = new JButton("+");
		strikePlus.setBounds(425, 465, 50, 20);
		frame.add(strikePlus);

		JButton strikeMinus = new JButton("-");
		strikeMinus.setBounds(425, 485, 50, 20);
		frame.add(strikeMinus);

		JButton ballPlus = new JButton("+");
		ballPlus.setBounds(575, 465, 50, 20);
		frame.add(ballPlus);

		JButton ballMinus = new JButton ("-");
		ballMinus.setBounds(575, 485, 50, 20);
		frame.add(ballMinus);

		JButton outPlus = new JButton ("+");
		outPlus.setBounds(725, 465, 50, 20);
		frame.add(outPlus);

		JButton outMinus = new JButton ("-");
		outMinus.setBounds(725, 485, 50, 20);
		frame.add(outMinus);

		JLabel numPitchesLabel = new JLabel ("Number of Pitches:");
		numPitchesLabel.setBounds(475, 430, 120, 20);
		frame.add(numPitchesLabel);

		enterNumPitches.setBounds(595, 430, 50, 20);
		frame.add(enterNumPitches);

		JButton pitchesPlus = new JButton("+");
		pitchesPlus.setBounds(655, 420, 50,20);
		frame.add(pitchesPlus);

		JButton pitchesMinus = new JButton("-");
		pitchesMinus.setBounds(655, 440, 50, 20);
		frame.add(pitchesMinus);

		JButton hit = new JButton ("Hit");
		hit.setBounds(475, 390, 75, 25);
		frame.add(hit);

		JButton foul = new JButton ("Foul");
		foul.setBounds(560, 390, 75, 25);
		frame.add(foul);

		JButton walk = new JButton ("Walk");
		walk.setBounds(645, 390, 75, 25);
		frame.add(walk);

		JLabel BatterStatsLabel = new JLabel ("Batter Stats");
		BatterStatsLabel.setBounds(10, 550, 100, 20);
		frame.add(BatterStatsLabel);

		JLabel BatterHitsLabel = new JLabel ("Hits");
		BatterHitsLabel.setBounds(110, 525, 75, 20);
		frame.add(BatterHitsLabel);

		JLabel BatterAtBatsLabel = new JLabel ("At Bats");
		BatterAtBatsLabel.setBounds(195, 525, 75, 20);
		frame.add(BatterAtBatsLabel);

		BatterHits.setBounds(110, 550, 75, 20);
		frame.add(BatterHits);

		BatterAtBats.setBounds(195, 550, 75, 20);
		frame.add(BatterAtBats);
		
		//////////////////////////////////////////////////////////////////////////////////
		JTextField home = new JTextField ("Home:");
		home.setBounds(30, 50, 100, 20);
		frame.add(home);
		hscoreLabel.setBounds(30, 90, 80, 20);
		frame.add(hscoreLabel);
		
		JButton hScorePlus = new JButton ("+");
		hScorePlus.setBounds(110, 80, 50, 20);
		frame.add(hScorePlus);

		
		JButton hScoreMinus = new JButton ("-");
		hScoreMinus.setBounds(110, 100, 50, 20);
		frame.add(hScoreMinus);

		hPitcherBatter.setBounds(10, 115, 200, 20);
		frame.add(hPitcherBatter);

		JTextField hAddPlayer = new JTextField ("");
		TextPrompt hAddtp = new TextPrompt("Enter Name Here", hAddPlayer);
		hAddtp.changeAlpha(0.5f);
		hAddPlayer.setBounds(10, 440, 125, 20);
		frame.add(hAddPlayer);

		JButton hUpdatePlayer = new JButton ("Update");
		hUpdatePlayer.setBounds(135, 440, 75, 20);
		frame.add(hUpdatePlayer);

		BasicArrowButton hUp = new BasicArrowButton(BasicArrowButton.NORTH);
		hUp.setBounds(215, 200, 20, 20);
		frame.add(hUp);

		BasicArrowButton hDown = new BasicArrowButton(BasicArrowButton.SOUTH);
		hDown.setBounds(215, 220, 20, 20);
		frame.add(hDown);

		JButton hRemove = new JButton ("Delete");
		hRemove.setBounds(170, 115, 75, 20);
		frame.add(hRemove);

		@SuppressWarnings({ "unchecked", "rawtypes" })
		JList hPlayersList = new JList (hPlayers.toArray());
		hPlayersList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		hPlayersList.setLayoutOrientation(JList.VERTICAL);
		hPlayersList.setVisibleRowCount(-1);
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setViewportView(hPlayersList);
		hPlayersList.setLayoutOrientation(JList.VERTICAL);
		scrollPane2.setBounds(10,140,200,300);
		frame.add(scrollPane2);

		//////////////////////////////////////////////////////////////////////////////////

		JButton vScorePlus = new JButton ("+");
		vScorePlus.setBounds(350, 80, 50, 20);
		frame.add(vScorePlus);
		
		JButton vScoreMinus = new JButton ("-");
		vScoreMinus.setBounds(350, 100, 50, 20);
		frame.add(vScoreMinus);

		JTextField visitor = new JTextField ("Visitor:");
		visitor.setBounds(270, 50, 100, 20);
		frame.add(visitor);
		
		vscoreLabel.setBounds(270, 90, 80, 20);
		frame.add(vscoreLabel);

		vPitcherBatter.setBounds(250, 115, 200, 20);
		frame.add(vPitcherBatter);

		JTextField vAddPlayer = new JTextField ("");
		TextPrompt vAddtp = new TextPrompt("Enter Name Here", vAddPlayer);
		vAddtp.changeAlpha(0.5f);
		vAddPlayer.setBounds(250, 440, 125, 20);
		frame.add(vAddPlayer);

		JButton vUpdatePlayer = new JButton ("Update");
		vUpdatePlayer.setBounds(375, 440, 75, 20);
		frame.add(vUpdatePlayer);

		BasicArrowButton vUp = new BasicArrowButton(BasicArrowButton.NORTH);
		vUp.setBounds(455, 200, 20, 20);
		frame.add(vUp);

		BasicArrowButton vDown = new BasicArrowButton(BasicArrowButton.SOUTH);
		vDown.setBounds(455, 220, 20, 20);
		frame.add(vDown);

		JButton vRemove = new JButton("Delete");
		vRemove.setBounds(410, 115, 75, 20);
		frame.add(vRemove);

		@SuppressWarnings({ "unchecked", "rawtypes" })
		JList vPlayersList = new JList (vPlayers.toArray());
		vPlayersList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		vPlayersList.setLayoutOrientation(JList.VERTICAL);
		vPlayersList.setVisibleRowCount(-1);
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setViewportView(vPlayersList);
		vPlayersList.setLayoutOrientation(JList.VERTICAL);
		scrollPane1.setBounds(250,140,200,300);
		frame.add(scrollPane1);
		////////////////////////////////////////////////////////////////////////////////

		finalT.setBounds(200, 10, 100, 20);
		frame.add(finalT);

		freeze.setBounds(400, 10, 100, 20);
		frame.add(freeze);
		
		enter.addActionListener(new ActionListener(){  //When the enter button is pressed, start the controller window and create the JSON file or overwrite it if it already exists
			@SuppressWarnings("unchecked")
	    	public void actionPerformed(ActionEvent e){
	    		File file = new File (filePath.getText());
	    		try {
					file.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		selectFile.setVisible(false);
	    		frame.setVisible(true);
	    		jsonFilePath = filePath.getText();
				csvFilePath = storageFilePath.getText();

				//Read And Load the CSV File
				if (new File (csvFilePath).exists()) {
					BufferedReader reader;
					try {
						reader = new BufferedReader(new FileReader(
								csvFilePath));
						String line = reader.readLine();
						line =reader.readLine();
						while (line != null) {
							String[] splitLine = line.split(",");
							if (!splitLine [0].isEmpty()) {
								hPlayers.add(splitLine[0]);
								hnumPitches.add(0);
								hMake.add(Integer.parseInt(splitLine[1]));
								hMiss.add(Integer.parseInt(splitLine[2]));
							}
							if (splitLine.length > 3) {
								vPlayers.add(splitLine[3]);
								vnumPitches.add(0);
								vMake.add(Integer.parseInt(splitLine[4]));
								vMiss.add(Integer.parseInt(splitLine[5]));
							}
							hPlayersList.setListData(hPlayers.toArray());
							vPlayersList.setListData(vPlayers.toArray());
							// read next line
							line = reader.readLine();
						}
						reader.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
	    	}  
	    });
		//Add a file finder window when the browse button for either the output or storage file is pressed
	    browse.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    	    FileNameExtensionFilter filter = new FileNameExtensionFilter("JavaScript Object Notation (.json)", "json");
				//FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma Seperated Values (.csv)", "csv");
	    	        chooser.setFileFilter(filter);
	    	    int returnVal = chooser.showOpenDialog(selectFile);
	    	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	       filePath.setText(chooser.getSelectedFile().getAbsolutePath());
	    	    }
	    	}  
	    }); 
		storageBrowse.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma Seperated Values (.csv)", "csv");
	    	        chooser.setFileFilter(filter);
	    	    int returnVal = chooser.showOpenDialog(selectFile);
	    	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	       storageFilePath.setText(chooser.getSelectedFile().getAbsolutePath());
	    	    }
	    	}  
	    }); 
		//Add functionality for each of the buttons of the controller and a little baseball automation for outs and innings
		periodPlus.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
	    		if (vPlayersList.getSelectedValue() == null || hPlayersList.getSelectedValue() == null) {
            		JOptionPane.showMessageDialog(new JFrame(), "Please Select Both a Batter and a Pitcher");
            	} else {
					if (!finalT.isSelected()) {
						if (top == 1) {
							top = 0;
							bottom = 1;
							periodString = "Bot. " + period;
							hPitcherBatter.setText("Batter:");
							vPitcherBatter.setText("Pitcher:");
							isPitcherHome = false;
							Batter = hPlayers.get(hBatter);
							make = hMake.get(hBatter);
							miss = hMiss.get(hBatter);
							hPlayersList.setSelectedIndex(hBatter);
							if (hBatter == hPlayers.size() - 1) {
								nextBatter = hPlayers.get(0);
								makeNext = hMake.get(0);
								missNext = hMiss.get(0);
							} else {
								nextBatter = hPlayers.get(hBatter + 1);
								makeNext = hMake.get(hBatter + 1);
								missNext = hMiss.get(hBatter + 1);
							}
							Pitcher = vPlayers.get(vPitcher);
							numPitches = vnumPitches.get(vPitcher);
							vPlayersList.setSelectedIndex(vPitcher);
						} else if (bottom == 1) {
							top = 1;
							bottom = 0;
							period++;
							periodString = "Top " + period;
							hPitcherBatter.setText("Pitcher:");
							vPitcherBatter.setText("Batter:");
							isPitcherHome = true;
							Batter = vPlayers.get(vBatter);
							make = vMake.get(vBatter);
							miss = vMiss.get(vBatter);
							vPlayersList.setSelectedIndex(vBatter);
							if (vBatter == vPlayers.size() - 1) {
								nextBatter = vPlayers.get(0);
								makeNext = vMake.get(0);
								missNext = vMiss.get(0);
							} else {
								nextBatter = vPlayers.get(vBatter + 1);
								makeNext = vMake.get(vBatter + 1);
								missNext = vMiss.get(vBatter + 1);
							}
							Pitcher = hPlayers.get(hPitcher);
							numPitches = hnumPitches.get(hPitcher);
							hPlayersList.setSelectedIndex(hPitcher);
						}
						outs = 0;
						firstBaseButton.setSelected(false);
						secondBaseButton.setSelected(false);
						thirdBaseButton.setSelected(false);
						thirdBase = 0;
						secondBase = 0;
						firstBase = 0;
						updateFile (jsonFilePath, csvFilePath);
					}
				}
	    	}  
	    }); 
		periodMinus.addActionListener(new ActionListener(){  
	    	public void actionPerformed(ActionEvent e){
				if (vPlayersList.getSelectedValue() == null || hPlayersList.getSelectedValue() == null) {
            		JOptionPane.showMessageDialog(new JFrame(), "Please Select Both a Batter and a Pitcher");
            	} else {	
					if (!finalT.isSelected()) {
						if (top == 1) {
							top = 0;
							bottom = 1;
							period--;
							periodString = "Bot. " + period;
							hPitcherBatter.setText("Batter:");
							vPitcherBatter.setText("Pitcher:");
							isPitcherHome = false;
							Batter = hPlayers.get(hBatter);
							make = vMake.get(hBatter);
							miss = vMiss.get(hBatter);
							hPlayersList.setSelectedIndex(hBatter);
							if (hBatter == hPlayers.size() - 1) {
								nextBatter = hPlayers.get(0);
								makeNext = hMake.get(hBatter + 1);
								missNext = hMiss.get(hBatter + 1);
							} else {
								nextBatter = hPlayers.get(hBatter + 1);
								makeNext = hMake.get(hBatter + 1);
								missNext = hMiss.get(hBatter + 1);
							}
							Pitcher = vPlayers.get(vPitcher);
							numPitches = vnumPitches.get(vPitcher);
							vPlayersList.setSelectedIndex(vPitcher);
						} else if (bottom == 1) {
							top = 1;
							bottom = 0;
							periodString = "Top " + period;
							hPitcherBatter.setText("Pitcher:");
							vPitcherBatter.setText("Batter:");
							isPitcherHome = true;
							Batter = vPlayers.get(vBatter);
							make = vMake.get(vBatter);
							miss = vMiss.get(vBatter);
							vPlayersList.setSelectedIndex(vBatter);
							if (vBatter == vPlayers.size() - 1) {
								nextBatter = vPlayers.get(0);
								makeNext = vMake.get(0);
								missNext = vMiss.get(0);
							} else {
								nextBatter = vPlayers.get(vBatter + 1);
								makeNext = vMake.get(vBatter + 1);
								missNext = vMiss.get(vBatter + 1);
							}
							Pitcher = hPlayers.get(hPitcher);
							numPitches = hnumPitches.get(hPitcher);
							hPlayersList.setSelectedIndex(hPitcher);
						}
						updateFile (jsonFilePath, csvFilePath);
					}
				}
	    	}  
	    }); 
		hScorePlus.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){
		    	hscore+=1;
		    	updateFile(jsonFilePath, csvFilePath);
		    }  
		 });
		hScoreMinus.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){
		    	hscore-=1;
		    	updateFile(jsonFilePath, csvFilePath);
		    }  
		});
		vScorePlus.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){
		    	vscore+=1;
		    	updateFile(jsonFilePath, csvFilePath);
		    }  
		});
		vScoreMinus.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){
		    	vscore-=1;
		    	updateFile(jsonFilePath, csvFilePath);
		    }  
		});
		strikePlus.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){
				if (vPlayersList.getSelectedValue() == null || hPlayersList.getSelectedValue() == null) {
            		JOptionPane.showMessageDialog(new JFrame(), "Please Select Both a Batter and a Pitcher");
            	} else {	
					if (strike < 2) {
						strike+=1;
					} else if (strike == 2) {
						strike=0;
						balls=0;
						outs++;
						if (isPitcherHome){
							vMiss.set(vPlayersList.getSelectedIndex(), vMiss.get(vPlayersList.getSelectedIndex())+1);
							if (vPlayersList.getSelectedIndex() == vPlayers.size() - 1) {
								vPlayersList.setSelectedIndex(0);
							} else {
								vPlayersList.setSelectedIndex(vPlayersList.getSelectedIndex() + 1);
							}
						} else {
							hMiss.set(hPlayersList.getSelectedIndex(), hMiss.get(hPlayersList.getSelectedIndex())+1);
							if (hPlayersList.getSelectedIndex() == hPlayers.size() - 1) {
								hPlayersList.setSelectedIndex(0);
							} else {
								hPlayersList.setSelectedIndex(hPlayersList.getSelectedIndex() + 1);
							}
						}
					}
					if (isPitcherHome) {
						hnumPitches.set(hPlayersList.getSelectedIndex(), hnumPitches.get(hPlayersList.getSelectedIndex())+1);
						numPitches = hnumPitches.get(hPlayersList.getSelectedIndex());
					} else {
						vnumPitches.set(vPlayersList.getSelectedIndex(), vnumPitches.get(vPlayersList.getSelectedIndex())+1);
						numPitches = vnumPitches.get(vPlayersList.getSelectedIndex());
					}
					updateFile(jsonFilePath, csvFilePath);
				}
		    }  
		});
		strikeMinus.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){	
					strike-=1;
					updateFile(jsonFilePath, csvFilePath);
		    }  
		});
		ballPlus.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){
				if (vPlayersList.getSelectedValue() == null || hPlayersList.getSelectedValue() == null) {
            		JOptionPane.showMessageDialog(new JFrame(), "Please Select Both a Batter and a Pitcher");
            	} else {	
					if (balls < 4) {
						balls+=1;
						if (isPitcherHome) {
							hnumPitches.set(hPlayersList.getSelectedIndex(), hnumPitches.get(hPlayersList.getSelectedIndex())+1);
							numPitches = hnumPitches.get(hPlayersList.getSelectedIndex());
						} else {
							vnumPitches.set(vPlayersList.getSelectedIndex(), vnumPitches.get(vPlayersList.getSelectedIndex())+1);
							numPitches = vnumPitches.get(vPlayersList.getSelectedIndex());
						}
					} else if (balls == 4) {
						strike = 0;
						balls = 0;
						if (isPitcherHome){
							if (vPlayersList.getSelectedIndex() == vPlayers.size() - 1) {
								vPlayersList.setSelectedIndex(0);
							} else {
								vPlayersList.setSelectedIndex(vPlayersList.getSelectedIndex() + 1);
							}
						} else {
							if (hPlayersList.getSelectedIndex() == hPlayers.size() - 1) {
								hPlayersList.setSelectedIndex(0);
							} else {
								hPlayersList.setSelectedIndex(hPlayersList.getSelectedIndex() + 1);
							}
						}
					}
					updateFile(jsonFilePath, csvFilePath);
				}
		    }  
		});
		ballMinus.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){
		    	balls-=1;
		    	updateFile(jsonFilePath, csvFilePath);
		    }  
		});
		outPlus.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){	
				outs+=1;
				updateFile(jsonFilePath, csvFilePath);
		    }  
		});
		outMinus.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){
		    	outs-=1;
		    	updateFile(jsonFilePath, csvFilePath);
		    }  
		});

		hit.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){
				if (vPlayersList.getSelectedValue() == null || hPlayersList.getSelectedValue() == null) {
            		JOptionPane.showMessageDialog(new JFrame(), "Please Select Both a Batter and a Pitcher");
            	} else {	
					if (isPitcherHome){
						vMiss.set(vPlayersList.getSelectedIndex(), vMiss.get(vPlayersList.getSelectedIndex())+1);
						vMake.set(vPlayersList.getSelectedIndex(), vMake.get(vPlayersList.getSelectedIndex())+1);
						if (vPlayersList.getSelectedIndex() == vPlayers.size() - 1) {
							vPlayersList.setSelectedIndex(0);
						} else {
							vPlayersList.setSelectedIndex(vPlayersList.getSelectedIndex() + 1);
						}
					} else {
						hMiss.set(hPlayersList.getSelectedIndex(), hMiss.get(hPlayersList.getSelectedIndex())+1);
						hMake.set(hPlayersList.getSelectedIndex(), hMake.get(hPlayersList.getSelectedIndex())+1);
						if (hPlayersList.getSelectedIndex() == hPlayers.size() - 1) {
							hPlayersList.setSelectedIndex(0);
						} else {
							hPlayersList.setSelectedIndex(hPlayersList.getSelectedIndex() + 1);
						}
					}
					strike = 0;
					balls = 0;
					if (isPitcherHome) {
						hnumPitches.set(hPlayersList.getSelectedIndex(), hnumPitches.get(hPlayersList.getSelectedIndex())+1);
						numPitches = hnumPitches.get(hPlayersList.getSelectedIndex());
					} else {
						vnumPitches.set(vPlayersList.getSelectedIndex(), vnumPitches.get(vPlayersList.getSelectedIndex())+1);
						numPitches = vnumPitches.get(vPlayersList.getSelectedIndex());
					}
					updateFile(jsonFilePath, csvFilePath);
				}
		    }  
		});

		foul.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){
	    		if (vPlayersList.getSelectedValue() == null || hPlayersList.getSelectedValue() == null) {
            		JOptionPane.showMessageDialog(new JFrame(), "Please Select Both a Batter and a Pitcher");
            	} else {
					if (strike < 2) {
						strike+=1;
					}
					if (isPitcherHome) {
						hnumPitches.set(hPlayersList.getSelectedIndex(), hnumPitches.get(hPlayersList.getSelectedIndex())+1);
						numPitches = hnumPitches.get(hPlayersList.getSelectedIndex());
					} else {
						vnumPitches.set(vPlayersList.getSelectedIndex(), vnumPitches.get(vPlayersList.getSelectedIndex())+1);
						numPitches = vnumPitches.get(vPlayersList.getSelectedIndex());
					}
					updateFile(jsonFilePath, csvFilePath);
				}
		    }  
		});

		walk.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){
				if (vPlayersList.getSelectedValue() == null || hPlayersList.getSelectedValue() == null) {
            		JOptionPane.showMessageDialog(new JFrame(), "Please Select Both a Batter and a Pitcher");
            	} else {	
					if (isPitcherHome){
						if (vPlayersList.getSelectedIndex() == vPlayers.size() - 1) {
							vPlayersList.setSelectedIndex(0);
						} else {
							vPlayersList.setSelectedIndex(vPlayersList.getSelectedIndex() + 1);
						}
					} else {
						if (hPlayersList.getSelectedIndex() == hPlayers.size() - 1) {
							hPlayersList.setSelectedIndex(0);
						} else {
							hPlayersList.setSelectedIndex(hPlayersList.getSelectedIndex() + 1);
						}
					}
					strike = 0;
					balls = 0;
					if (isPitcherHome) {
						hnumPitches.set(hPlayersList.getSelectedIndex(), hnumPitches.get(hPlayersList.getSelectedIndex())+1);
						numPitches = hnumPitches.get(hPlayersList.getSelectedIndex());
					} else {
						vnumPitches.set(vPlayersList.getSelectedIndex(), vnumPitches.get(vPlayersList.getSelectedIndex())+1);
						numPitches = vnumPitches.get(vPlayersList.getSelectedIndex());
					}
					updateFile(jsonFilePath, csvFilePath);
				}
		    }  
		});

		hUpdatePlayer.addActionListener(new ActionListener(){  
			@SuppressWarnings("unchecked")
		    public void actionPerformed(ActionEvent e){	
				if (hAddPlayer.getText().equals("")) {
            		JOptionPane.showMessageDialog(new JFrame(), "Please Enter the Name to Update to");
            	} else {		
					if (hPlayersList.getSelectedValue() == null) {
						JOptionPane.showMessageDialog(new JFrame(), "Please Select Both a Batter and a Pitcher");
					} else {
						int selectedIndex = hPlayersList.getSelectedIndex();
						hPlayers.set(selectedIndex, hAddPlayer.getText());
						hPlayersList.setListData(hPlayers.toArray());
						hPlayersList.setSelectedIndex(selectedIndex);
						hAddPlayer.setText("");
						updateFile(jsonFilePath, csvFilePath);
					}
				}
		    }  
		});

		vUpdatePlayer.addActionListener(new ActionListener(){  
			@SuppressWarnings("unchecked")
		    public void actionPerformed(ActionEvent e){	
				if (vAddPlayer.getText().equals("")) {
            		JOptionPane.showMessageDialog(new JFrame(), "Please Enter the Name to Update to");
            	} else {		
					if (vPlayersList.getSelectedValue() == null) {
						JOptionPane.showMessageDialog(new JFrame(), "Please Select Both a Batter and a Pitcher");
					} else {
						int selectedIndex = vPlayersList.getSelectedIndex();
						vPlayers.set(selectedIndex, vAddPlayer.getText());
						vPlayersList.setListData(vPlayers.toArray());
						vPlayersList.setSelectedIndex(selectedIndex);
						vAddPlayer.setText("");
						updateFile(jsonFilePath, csvFilePath);
					}
				}
		    }  
		});

		pitchesPlus.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){
				if (vPlayersList.getSelectedValue() == null || hPlayersList.getSelectedValue() == null) {
            		JOptionPane.showMessageDialog(new JFrame(), "Please Select Both a Batter and a Pitcher");
            	} else {	
					if (isPitcherHome) {
						hnumPitches.set(hPlayersList.getSelectedIndex(), hnumPitches.get(hPlayersList.getSelectedIndex())+1);
						numPitches = hnumPitches.get(hPlayersList.getSelectedIndex());
					} else {
						vnumPitches.set(vPlayersList.getSelectedIndex(), vnumPitches.get(vPlayersList.getSelectedIndex())+1);
						numPitches = vnumPitches.get(vPlayersList.getSelectedIndex());
					}
					updateFile(jsonFilePath, csvFilePath);
				}
		    }  
		});

		pitchesMinus.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){
				if (vPlayersList.getSelectedValue() == null || hPlayersList.getSelectedValue() == null) {
            		JOptionPane.showMessageDialog(new JFrame(), "Please Select Both a Batter and a Pitcher");
            	} else {	
					if (isPitcherHome) {
						if (hnumPitches.get(hPlayersList.getSelectedIndex()) > 0) {
							hnumPitches.set(hPlayersList.getSelectedIndex(), hnumPitches.get(hPlayersList.getSelectedIndex())-1);
						}
						numPitches = hnumPitches.get(hPlayersList.getSelectedIndex());
					} else {
						if (vnumPitches.get(vPlayersList.getSelectedIndex()) > 0) {
							vnumPitches.set(vPlayersList.getSelectedIndex(),vnumPitches.get(vPlayersList.getSelectedIndex())-1);
						}
						numPitches = vnumPitches.get(vPlayersList.getSelectedIndex());
					}
					updateFile(jsonFilePath, csvFilePath);
				}
		    }  
		});

		hRemove.addActionListener(new ActionListener(){  
		    @SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e){
				if (vPlayersList.getSelectedValue() == null || hPlayersList.getSelectedValue() == null) {
            		JOptionPane.showMessageDialog(new JFrame(), "Please Select Both a Batter and a Pitcher");
            	} else {	
					hnumPitches.remove(hPlayersList.getSelectedIndex());
					hPlayers.remove(hPlayersList.getSelectedIndex());
					hPlayersList.setListData(hPlayers.toArray());
					updateFile(jsonFilePath, csvFilePath);
				}
		    }  
		});

		vRemove.addActionListener(new ActionListener(){  
		    @SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e){
				if (vPlayersList.getSelectedValue() == null || hPlayersList.getSelectedValue() == null) {
            		JOptionPane.showMessageDialog(new JFrame(), "Please Select Both a Batter and a Pitcher");
            	} else {	
					vnumPitches.remove(vPlayersList.getSelectedIndex());
					vPlayers.remove(vPlayersList.getSelectedIndex());
					vPlayersList.setListData(vPlayers.toArray());
					updateFile(jsonFilePath, csvFilePath);
				}
		    }  
		});

		hUp.addActionListener(new ActionListener(){  
		    @SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e){
				if (vPlayersList.getSelectedValue() == null || hPlayersList.getSelectedValue() == null) {
            		JOptionPane.showMessageDialog(new JFrame(), "Please Select Both a Batter and a Pitcher");
            	} else {	
					int selectedIndex = hPlayersList.getSelectedIndex();
					String temp = hPlayers.get(selectedIndex);
					hPlayers.set(selectedIndex, hPlayers.get(selectedIndex - 1));
					hPlayers.set(selectedIndex - 1, temp);
					hPlayersList.setListData(hPlayers.toArray());
					int temp2 = hnumPitches.get(selectedIndex);
					hnumPitches.set(selectedIndex, hnumPitches.get(selectedIndex - 1));
					hnumPitches.set(selectedIndex - 1, temp2);
					hPlayersList.setSelectedIndex(selectedIndex - 1);
					updateFile(jsonFilePath, csvFilePath);
				}
		    }  
		});

		hDown.addActionListener(new ActionListener(){  
		    @SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e){
				if (vPlayersList.getSelectedValue() == null || hPlayersList.getSelectedValue() == null) {
            		JOptionPane.showMessageDialog(new JFrame(), "Please Select Both a Batter and a Pitcher");
            	} else {	
					int selectedIndex = hPlayersList.getSelectedIndex();
					String temp = hPlayers.get(selectedIndex);
					hPlayers.set(selectedIndex, hPlayers.get(selectedIndex + 1));
					hPlayers.set(selectedIndex + 1, temp);
					hPlayersList.setListData(hPlayers.toArray());
					int temp2 = hnumPitches.get(selectedIndex);
					hnumPitches.set(selectedIndex, hnumPitches.get(selectedIndex + 1));
					hnumPitches.set(selectedIndex + 1, temp2);
					hPlayersList.setSelectedIndex(selectedIndex + 1);
					updateFile(jsonFilePath, csvFilePath);
				}
		    }  
		});

		vUp.addActionListener(new ActionListener(){  
		    @SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e){
				if (vPlayersList.getSelectedValue() == null || hPlayersList.getSelectedValue() == null) {
            		JOptionPane.showMessageDialog(new JFrame(), "Please Select Both a Batter and a Pitcher");
            	} else {	
					int selectedIndex = vPlayersList.getSelectedIndex();
					String temp = vPlayers.get(selectedIndex);
					vPlayers.set(selectedIndex, vPlayers.get(selectedIndex - 1));
					vPlayers.set(selectedIndex - 1, temp);
					vPlayersList.setListData(vPlayers.toArray());
					int temp2 = vnumPitches.get(selectedIndex);
					vnumPitches.set(selectedIndex, vnumPitches.get(selectedIndex - 1));
					vnumPitches.set(selectedIndex - 1, temp2);
					vPlayersList.setSelectedIndex(selectedIndex - 1);
					updateFile(jsonFilePath, csvFilePath);
				}
		    }  
		});

		vDown.addActionListener(new ActionListener(){  
		    @SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e){
				if (vPlayersList.getSelectedValue() == null || hPlayersList.getSelectedValue() == null) {
            		JOptionPane.showMessageDialog(new JFrame(), "Please Select Both a Batter and a Pitcher");
            	} else {	
					int selectedIndex = vPlayersList.getSelectedIndex();
					String temp = vPlayers.get(selectedIndex);
					vPlayers.set(selectedIndex, vPlayers.get(selectedIndex + 1));
					vPlayers.set(selectedIndex + 1, temp);
					vPlayersList.setListData(vPlayers.toArray());
					int temp2 = vnumPitches.get(selectedIndex);
					vnumPitches.set(selectedIndex, vnumPitches.get(selectedIndex + 1));
					vnumPitches.set(selectedIndex + 1, temp2);
					vPlayersList.setSelectedIndex(selectedIndex + 1);
					updateFile(jsonFilePath, csvFilePath);
				}
		    }  
		});


		enterNumPitches.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if (isPitcherHome) {
					hnumPitches.set(hPlayersList.getSelectedIndex(), Integer.parseInt(enterNumPitches.getText()));
					numPitches = hnumPitches.get(hPlayersList.getSelectedIndex());
				} else {
					vnumPitches.set(vPlayersList.getSelectedIndex(), Integer.parseInt(enterNumPitches.getText()));
					numPitches = vnumPitches.get(vPlayersList.getSelectedIndex());
				}
		    	updateFile(jsonFilePath, csvFilePath);
			}
		});

		hAddPlayer.addActionListener(new java.awt.event.ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(java.awt.event.ActionEvent e) {
				hPlayers.add(hAddPlayer.getText());
				hnumPitches.add(0);
				hMake.add(0);
				hMiss.add(0);
				hPlayersList.setListData(hPlayers.toArray());
				hAddPlayer.setText("");
				updateFile(jsonFilePath, csvFilePath);
			}
		});

		vAddPlayer.addActionListener(new java.awt.event.ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(java.awt.event.ActionEvent e) {
				vPlayers.add(vAddPlayer.getText());
				vnumPitches.add(0);
				vMake.add(0);
				vMiss.add(0);
				vPlayersList.setListData(vPlayers.toArray());
				vAddPlayer.setText("");
				updateFile(jsonFilePath, csvFilePath);
			}
		});

		BatterHits.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if (isPitcherHome){
					vMake.set(vPlayersList.getSelectedIndex(), Integer.parseInt(BatterHits.getText()));
				} else {
					hMake.set(hPlayersList.getSelectedIndex(), Integer.parseInt(BatterHits.getText()));
				}
				updateFile(jsonFilePath, csvFilePath);
			}
		});

		BatterAtBats.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if (isPitcherHome){
					vMiss.set(vPlayersList.getSelectedIndex(), Integer.parseInt(BatterAtBats.getText()));
				} else {
					hMiss.set(hPlayersList.getSelectedIndex(), Integer.parseInt(BatterAtBats.getText()));
				}
				updateFile(jsonFilePath, csvFilePath);
			}
		});

		hPlayersList.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (hPlayersList.getSelectedIndex() >= 0) {	
					if (isPitcherHome) {
						Pitcher = hPlayers.get(hPlayersList.getSelectedIndex());
						numPitches = hnumPitches.get(hPlayersList.getSelectedIndex());
						hPitcher = hPlayersList.getSelectedIndex();
					} else {
						Batter = hPlayers.get(hPlayersList.getSelectedIndex());
						make = hMake.get(hPlayersList.getSelectedIndex());
						miss = hMiss.get(hPlayersList.getSelectedIndex());
						if (hPlayersList.getSelectedIndex() == (hPlayers.size() - 1)) {
							nextBatter = hPlayers.get(0);
							makeNext = hMake.get(0);
							missNext = hMiss.get(0);
						} else {
							nextBatter = hPlayers.get(hPlayersList.getSelectedIndex() + 1);
							makeNext = hMake.get(hPlayersList.getSelectedIndex() + 1);
							missNext = hMiss.get(hPlayersList.getSelectedIndex() + 1);
						}
						hBatter = hPlayersList.getSelectedIndex();
					}
					updateFile(jsonFilePath, csvFilePath);
				}
			}
		});	

		vPlayersList.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (vPlayersList.getSelectedIndex() >= 0) {	
					if (isPitcherHome) {
						Batter = vPlayers.get(vPlayersList.getSelectedIndex());
						make = vMake.get(vPlayersList.getSelectedIndex());
						miss = vMiss.get(vPlayersList.getSelectedIndex());
						if (vPlayersList.getSelectedIndex() == (vPlayers.size() - 1)) {
							nextBatter = vPlayers.get(0);
							makeNext = vMake.get(0);
							missNext = vMiss.get(0);
						} else {
							nextBatter = vPlayers.get(vPlayersList.getSelectedIndex() + 1);
							makeNext = vMake.get(vPlayersList.getSelectedIndex() + 1);
							missNext = vMiss.get(vPlayersList.getSelectedIndex()+ 1);
						}
						vBatter = vPlayersList.getSelectedIndex();
					} else {
						Pitcher = vPlayers.get(vPlayersList.getSelectedIndex());
						numPitches = vnumPitches.get(vPlayersList.getSelectedIndex());
						vPitcher = vPlayersList.getSelectedIndex();
					}
					updateFile(jsonFilePath, csvFilePath);
				}
			}
		});	

		freeze.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {	
				if(ev.getStateChange()==ItemEvent.SELECTED){
					freezeVar = true;
				} else if(ev.getStateChange()==ItemEvent.DESELECTED){
					freezeVar = false;
				}
				updateFile(jsonFilePath, csvFilePath);
			}				    	  
		});
		finalT.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				if(ev.getStateChange()==ItemEvent.SELECTED){
					periodString = "Final";
				} else if(ev.getStateChange()==ItemEvent.DESELECTED){
					if (top == 1) {
						periodString = "Top "+ period;
					} else if (bottom == 1) {
						periodString = "Bot. " + period;
					}
				}
				updateFile(jsonFilePath, csvFilePath);
				}			    	  
		});
			
		JButton reset = new JButton ("Reset");
		reset.setBounds(300, 10, 100, 20);
		reset.setBackground(Color.RED);
		reset.setForeground(Color.WHITE);
		frame.add(reset);
			
		firstBaseButton.setBounds(20, 475, 100, 20);
		frame.add(firstBaseButton);

		secondBaseButton.setBounds(120, 475, 100, 20);
		frame.add(secondBaseButton);

		thirdBaseButton.setBounds(220, 475, 100, 20);
		frame.add(thirdBaseButton);

		firstBaseButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {	
				if(ev.getStateChange()==ItemEvent.SELECTED){
					firstBase = 1;
				} else if(ev.getStateChange()==ItemEvent.DESELECTED){
					firstBase = 0;
				}
				updateFile(jsonFilePath, csvFilePath);
			}				    	  
		});

		secondBaseButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {	
				if(ev.getStateChange()==ItemEvent.SELECTED){
					secondBase = 1;
				} else if(ev.getStateChange()==ItemEvent.DESELECTED){
					secondBase = 0;
				}
				updateFile(jsonFilePath, csvFilePath);
			}				    	  
		});

		thirdBaseButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {	
				if(ev.getStateChange()==ItemEvent.SELECTED){
					thirdBase = 1;
				} else if(ev.getStateChange()==ItemEvent.DESELECTED){
					thirdBase = 0;
				}
				updateFile(jsonFilePath, csvFilePath);
			}				    	  
		});

		reset.addActionListener(new ActionListener(){  
		    public void actionPerformed(ActionEvent e){
		    	period = 1;
		    	periodString = "Top 1";
				isPitcherHome = true;
				hPitcherBatter.setText("Pitcher:");
				vPitcherBatter.setText("Batter:");
				hPlayersList.setSelectedIndex(0);
				vPlayersList.setSelectedIndex(0);
				Batter = vPlayers.get(0);
				make = vMake.get(0);
				miss = vMiss.get(0);
				Pitcher =hPlayers.get(0);
				numPitches = 0;
				hPitcher = 0;
				hBatter = 0;
				vPitcher = 0;
				vBatter = 0;
		    	hscore = 0;
		    	vscore = 0;
				top = 1;
				bottom = 0;
				outs = 0;
				balls = 0;
				strike = 0;
				firstBase = 0;
				firstBaseButton.setSelected(false);
				secondBase = 0;
				secondBaseButton.setSelected(false);
				thirdBase = 0;
				thirdBaseButton.setSelected(false);
				enterNumPitches.setText("0");
				for (int i = 0; i < vnumPitches.size(); i++){
					vnumPitches.set(i, 0);
				}
				for (int i = 0; i < hnumPitches.size(); i++){
					hnumPitches.set(i, 0);
				}
		    	updateFile(jsonFilePath, csvFilePath);
		    }  
		});
		//Add functionality for vMix input control
			JLabel ipaddressLabel = new JLabel ("Enter The Web Address IP for vMix:");
			ipaddressLabel.setBounds(550, 10, 250, 20);
			frame.add(ipaddressLabel);
			
			JTextField ipaddress = new JTextField ("Enter IP Address Here");
			ipaddress.setBounds(500, 35, 250, 20);
			frame.add(ipaddress);
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			JList inputsList = new JList (inputs.toArray());
		    inputsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		    inputsList.setLayoutOrientation(JList.VERTICAL);
		    inputsList.setVisibleRowCount(-1);
		    JScrollPane scrollPane = new JScrollPane();
		    scrollPane.setViewportView(inputsList);
		    inputsList.setLayoutOrientation(JList.VERTICAL);
		    scrollPane.setBounds(625,60,125,230);
		    frame.add(scrollPane);
		    
			@SuppressWarnings({ "unchecked", "rawtypes" })
			JList previewList = new JList (inputs.toArray());
		    previewList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		    previewList.setLayoutOrientation(JList.VERTICAL);
		    previewList.setVisibleRowCount(-1);
		    JScrollPane previewscrollPane = new JScrollPane();
		    previewscrollPane.setViewportView(previewList);
		    previewList.setLayoutOrientation(JList.VERTICAL);
		    previewscrollPane.setBounds(500,60,125,230);
		    frame.add(previewscrollPane);
			
			JTextField add = new JTextField ("1 - scoreboard");
			add.setBounds(500, 300, 250, 20);
			frame.add(add);
			
			JLabel addLabel = new JLabel ("Add the Inputs in vMix in Order:");
			addLabel.setBounds(500, 275, 250, 20);
			frame.add(addLabel);
			
	        add.addActionListener(new java.awt.event.ActionListener() {
	            @SuppressWarnings("unchecked")
				public void actionPerformed(java.awt.event.ActionEvent e) {
	            	inputs.add(add.getText());
	            	inputsList.setListData(inputs.toArray());
	            	previewList.setListData(inputs.toArray());
	            }
	        });
	        
	        JButton remove = new JButton ("Remove");
	        remove.setBounds(500, 325, 250, 20);
	        frame.add(remove);
	        
	        remove.addActionListener(new java.awt.event.ActionListener() {
	            @SuppressWarnings("unchecked")
				public void actionPerformed(java.awt.event.ActionEvent e) {
	            	inputs.remove(inputsList.getSelectedIndex());
	            	inputsList.setListData(inputs.toArray());
	            	previewList.setListData(inputs.toArray());
	            }
	        });
	        
	        inputsList.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (ipaddress.getText().equals("Enter IP Address Here")) {
	            		JOptionPane.showMessageDialog(new JFrame(), "Please Enter An IP Address");
	            	} else {
	            		try {
	            			String url = ipaddress.getText()+"/API/?Function=Cut&Duration=10&Input="+(inputsList.getSelectedIndex()+1);      
	            			URL obj = new URL(url);
	            			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	            			con.setRequestMethod("GET");
	            			con.setRequestProperty("User-Agent", "Mozilla/5.0");
	            			@SuppressWarnings("unused")
	            			int responseCode = con.getResponseCode();
	            		} catch (ProtocolException e1) {
	            			// TODO Auto-generated catch block
	            			e1.printStackTrace();
	            		}
	            		//add request header
	            		catch (IOException e1) {
	            			// TODO Auto-generated catch block
	            			e1.printStackTrace();
	            		}
	            	}
				}
	        });	
	        
	        previewList.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (ipaddress.getText().equals("Enter IP Address Here")) {
	            		JOptionPane.showMessageDialog(new JFrame(), "Please Enter An IP Address");
	            	} else {
	            		try {
	            			String url = ipaddress.getText()+"/API/?Function=PreviewInput&Input="+(previewList.getSelectedIndex()+1);      
	            			URL obj = new URL(url);
	            			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	            			con.setRequestMethod("GET");
	            			con.setRequestProperty("User-Agent", "Mozilla/5.0");
	            			@SuppressWarnings("unused")
	            			int responseCode = con.getResponseCode();
	            		} catch (ProtocolException e1) {
	            			// TODO Auto-generated catch block
	            			e1.printStackTrace();
	            		}
	            		//add request header
	            		catch (IOException e1) {
	            			// TODO Auto-generated catch block
	            			e1.printStackTrace();
	            		}
	            	}
				}
	        });	
	        
	        JButton previewtoProgram = new JButton ("Preview -> Program");
	        previewtoProgram.setBounds(500, 350, 250, 20);
	        frame.add(previewtoProgram);
	        
	        previewtoProgram.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
		            try {
						String url = ipaddress.getText()+"/API/?Function=Cut&Duration=10&Input=0";      
						URL obj = new URL(url);
						HttpURLConnection con = (HttpURLConnection) obj.openConnection();
						con.setRequestMethod("GET");
						con.setRequestProperty("User-Agent", "Mozilla/5.0");
						@SuppressWarnings("unused")
						int responseCode = con.getResponseCode();
					} catch (ProtocolException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            //add request header
		            catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
	        });
	}
	//Method to update the JSON file
	public static void updateFile (String filePath, String CSVfilePath) {
		if (strike < 0) {
			strike = 0;
		}
		if (outs < 0) {
			outs = 0;
		}
		if (outs > 3) {
			outs = 3;
		}
		if (balls < 0) {
			balls = 0;
		}
		if (!freezeVar) {
			try {

				String filePeriodString = " ";
				int topFile = 0;
				int bottomFile = 0;
				String homenumPitches = " ";
				String homePitcher = "";
				String visitornumPitches = " ";
				String visitorPitcher = " ";
				String homeBatter = " ";
				String homenextBatter = " ";
				String homeBatterAvg = " ";
				String homenextBatterAvg = " ";
				String visitorBatter = " ";
				String visitornextBatter = " ";
				String visitorBatterAvg = " ";
				String visitornextBatterAvg = " ";
				int out1 = 0;
				int out2 = 0;
				int out3 = 0;

				if (finalT.isSelected()) {
					filePeriodString = "Final";
					topFile = 0;
					bottomFile = 0;
					homenumPitches = " ";
					homePitcher = " ";
					visitornumPitches = " ";
					visitorPitcher = " ";
					homeBatter = " ";
					homenextBatter = " ";
					homeBatterAvg = " ";
					homenextBatterAvg = " ";
					visitorBatter = " ";
					visitornextBatter = " ";
					visitorBatterAvg = " ";
					visitornextBatterAvg = " ";
				} else {
					filePeriodString = ""+period;
					topFile = top;
					bottomFile = bottom;
					if (isPitcherHome) {
						homePitcher = "Pitching: " + Pitcher;
						homenumPitches = "P:" + numPitches;
						visitorBatter = "Up To Bat: " + Batter;
						visitornextBatter = "Batting Next: " + nextBatter;
						if (miss > 0){
							double avg = (double)make / (double)miss;
							visitorBatterAvg = String.format("%.3f", avg);
						} else {
							visitorBatterAvg = " ";
						}
						if (missNext > 0) {
							double avg = (double)makeNext / (double)missNext;
							visitornextBatterAvg = String.format("%.3f", avg);
						} else {
							visitornextBatterAvg = " ";
						}
						homeBatter = " ";
						homenextBatter = " ";
						homeBatterAvg = " ";
						homenextBatterAvg = " ";
					} else {
						visitorPitcher = "Pitching: "+Pitcher;
						visitornumPitches = "P:" + numPitches;
						homeBatter = "Up To Bat: " +Batter;
						homenextBatter = "Batting Next: " +nextBatter;
						if (miss > 0){
							double avg = (double)make / (double)miss;
							homeBatterAvg = String.format("%.3f", avg);
						} else {
							homeBatterAvg = " ";
						}
						if (missNext > 0) {
							double avg = (double)makeNext / (double)missNext;
							homenextBatterAvg = String.format("%.3f", avg);
						} else {
							homenextBatterAvg = " ";
						}
						visitorBatter = " ";
						visitornextBatter = " ";
						visitorBatterAvg = " ";
						visitornextBatterAvg = " ";
					}
				}
				if (outs == 3) {
					homeBatter = " ";
					homenextBatter = " ";
					homeBatterAvg = " ";
					homenextBatterAvg = " ";
					visitorBatter = " ";
					visitornextBatter = " ";
					visitorBatterAvg = " ";
					visitornextBatterAvg = " ";
				}
				if (outs == 0) {
					out1 = 0;
					out2 = 0;
					out3 = 0;
				} else if (outs == 1) {
					out1 = 1;
					out2 = 0;
					out3 = 0;
				} else if (outs == 2) {
					out1 = 1;
					out2 = 1;
					out3 = 0;
				} else {
					out1 = 1;
					out2 = 1;
					out3 = 1;
				}
				

				FileWriter Fwriter = new FileWriter(new File (filePath));
				BufferedWriter writer = new BufferedWriter(Fwriter);
				//writer.write("TopofInning, BottomofInning, Inning, HomeScore, VisitorScore, Strikes, Out1, Out2, Out3, Balls, HomeBatter, HomeNextBatter, HomeBatterAvg, HomeNextBatterAvg, VisitorBatter, VisitorNextBatter, VisitorBatterAvg, VisitornextBatterAvg, FirstBase, SecondBase, ThirdBase, HomeNumberofPitches, HomePitcher, VisitorNumberofPitches, VisitorPitcher\n"
				//			+ topFile + "," + bottomFile + ","+filePeriodString + "," + hscore + ","+vscore+","+strike+","+out1+","+out2+","+out3+","+balls+","+homeBatter+","+homenextBatter+","+homeBatterAvg+","+homenextBatterAvg+","+visitorBatter+","+visitornextBatter+","+visitorBatterAvg+","+visitornextBatterAvg+","+firstBase+","+secondBase+","+thirdBase+","+homenumPitches+","+homePitcher+","+visitornumPitches+","+visitorPitcher);

				writer.write("[{\"TopofInning\":\""+topFile+ "\",\"BottomofInning\":\""+bottomFile+ "\", \"Inning\":\""+filePeriodString+"\",\"HomeScore\":\""+hscore+ "\",\"VisitorScore\":\""+vscore+"\",\"Strikes\":\""+strike+"\",\"Out1\":\""+out1+ "\",\"Out2\":\""+out2+ "\",\"Out3\":\""+out3+ "\",\"Balls\":\""+balls+ "\",\"HomeBatter\":\""+homeBatter+ "\",\"HomeNextBatter\":\""+homenextBatter+ "\",\"HomeBatterAvg\":\""+homeBatterAvg+ "\",\"HomeNextBatterAvg\":\""+homenextBatterAvg+ "\",\"VisitorBatter\":\""+visitorBatter+ "\",\"VisitorNextBatter\":\""+visitornextBatter+ "\",\"VisitorBatterAvg\":\""+visitorBatterAvg+ "\",\"VisitorNextBatterAvg\":\""+visitornextBatterAvg+ "\",\"FirstBase\":\""+firstBase+ "\",\"SecondBase\":\""+secondBase+ "\",\"ThirdBase\":\""+thirdBase+ "\",\"HomeNumberofPitches\":\""+homenumPitches+ "\",\"HomePitcher\":\""+homePitcher+ "\",\"VisitorNumberofPitches\":\""+visitornumPitches+ "\",\"VisitorPitcher\":\""+visitorPitcher+ "\"}]");
				writer.close();



			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try {

			String written_string = "Home Name, Home Hits, Home At Bats, Visitor Name, Visitor Hits, Visitor At Bats\n";
			for (int i = 0; i < hPlayers.size() && i < vPlayers.size(); i ++) {
				written_string += (hPlayers.get(i) + "," + hMake.get(i) + "," + hMiss.get(i) + "," + vPlayers.get(i) + "," + vMake.get(i) + "," + vMiss.get(i) + "\n");
			}
			if (hPlayers.size() > vPlayers.size()){
				for (int i = vPlayers.size(); i < hPlayers.size(); i++) {
					written_string += (hPlayers.get(i) + "," + hMake.get(i) + "," + hMiss.get(i) + ",,,\n");
				}
			} else {
				for (int i = hPlayers.size(); i < vPlayers.size(); i++) {
					written_string += (",,," + vPlayers.get(i) + "," + vMake.get(i) + "," + vMiss.get(i) + "\n");
				}
			}
			FileWriter Fwriter1 = new FileWriter(new File (CSVfilePath));
			BufferedWriter writer1 = new BufferedWriter(Fwriter1);
			writer1.write(written_string);
			writer1.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}				
		periodLabel.setText("Inning: " + periodString);
		hscoreLabel.setText("Score: " + hscore);
		vscoreLabel.setText("Score: " + vscore);
		strikesLabel.setText("Strikes: " + strike);
		ballsLabel.setText("Balls: " + balls);
		outsLabel.setText("Outs: " + outs);
		enterNumPitches.setText(""+numPitches);
		if (isPitcherHome) {
			BatterHits.setText(""+vMake.get(vBatter));
			BatterAtBats.setText(""+vMiss.get(vBatter));
		} else {
			BatterHits.setText(""+hMake.get(hBatter));
			BatterAtBats.setText(""+hMiss.get(hBatter));
		}
	}
}
