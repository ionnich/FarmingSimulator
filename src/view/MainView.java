package view;

import controller.MainViewController;

import javax.swing.*;

public class MainView{

    private final JFrame mainFrame;

    public MainView(){

        this.mainFrame = new JFrame("MyFarmMVC");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setResizable(false);
        this.mainFrame.setSize(1200, 800);
        this.mainFrame.setLayout(null);
    }

    public JFrame getMainFrame(){
        return this.mainFrame;
    }
}
