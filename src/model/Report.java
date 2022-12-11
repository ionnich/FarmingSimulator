package model;

/**
 * The Report class contains a report for an action done in the game.
 */
public class Report {

    private final String status;
    private final Boolean success;

    /**
     * Instantiates a new Report.
     *
     * @param status  the status
     * @param success the success
     */
    public Report(String status, Boolean success) {
        this.status = status;
        this.success = success;
    }

    /**
     * Gets the report status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets the success of the report.
     *
     * @return the boolean success
     */
    public Boolean isSuccess() {
        return success;
    }

}
