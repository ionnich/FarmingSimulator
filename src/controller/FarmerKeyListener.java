package controller;

import model.tiles.RockyTile;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FarmerKeyListener {

    FarmerViewController fvc;
    FarmlandController flc;

    public FarmerKeyListener(FarmerViewController fvc, FarmlandController flc){
        this.fvc = fvc;
        this.flc = flc;
    }

    public void moveFarmerRight(){

        System.out.println("Farmer move right invoked");

        this.fvc.changeCurrentTile(
                this.flc.getTile(
                        this.fvc.getCurrentTile().getX() + 1,
                        this.fvc.getCurrentTile().getY()
                ),
                this.flc.getTileView(
                this.fvc.getCurrentTile().getX() + 1,
                    this.fvc.getCurrentTile().getY()
                )
        );

    }

    public void moveFarmerLeft() {

        System.out.println("current tile on invoke");
        System.out.println(this.fvc.getCurrentTile().getClass());


        this.fvc.changeCurrentTile(
                this.flc.getTile(
                        this.fvc.getCurrentTile().getX() - 1,
                        this.fvc.getCurrentTile().getY()
                ),
                this.flc.getTileView(
                        this.fvc.getCurrentTile().getX() - 1,
                        this.fvc.getCurrentTile().getY()
                )
        );
    }

    public void moveFarmerUp() {

        System.out.println("Farmer move down invoked");

        this.fvc.changeCurrentTile(
                this.flc.getTile(
                        this.fvc.getCurrentTile().getX() ,
                        this.fvc.getCurrentTile().getY() + 1
                ),
                this.flc.getTileView(
                        this.fvc.getCurrentTile().getX(),
                        this.fvc.getCurrentTile().getY() + 1
                )
        );

    }

    public void moveFarmerDown() {

        System.out.println("Farmer move up invoked");
        this.fvc.changeCurrentTile(
                this.flc.getTile(
                        this.fvc.getCurrentTile().getX(),
                        this.fvc.getCurrentTile().getY() - 1
                ),
                this.flc.getTileView(
                        this.fvc.getCurrentTile().getX(),
                        this.fvc.getCurrentTile().getY() - 1
                )
        );

    }
}
