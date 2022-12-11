package view;

import controller.MainViewController;

import javax.swing.*;

/**
 * The MainView class is responsible for displaying the mainframe.
 */
public class MainView{

    private final JFrame mainFrame;

    /**
     * Instantiates a new MainView.
     */
    public MainView(){

        this.mainFrame = new JFrame("MyFarmMVC");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setResizable(false);
        this.mainFrame.setSize(1200, 800);
        this.mainFrame.setLayout(null);
    }

    /**
     * Gets the main frame.
     *
     * @return the main frame
     */
    public JFrame getMainFrame(){
        return this.mainFrame;
    }
}
