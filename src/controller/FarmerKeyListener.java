package controller;

/**
 *  The FarmerKeyListener class is responsible for listening to key events related to the Farmer.
 */
public class FarmerKeyListener {
    FarmerViewController fvc;
    FarmlandController flc;

    /**
     * Instantiates a new Farmer key listener.
     *
     * @param fvc the fvc
     * @param flc the flc
     */
    public FarmerKeyListener(FarmerViewController fvc, FarmlandController flc){
        this.fvc = fvc;
        this.flc = flc;
    }

    /**
     * Move farmer right.
     */
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

    /**
     * Move farmer left.
     */
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

    /**
     * Move farmer up.
     */
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

    /**
     * Move farmer down.
     */
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
