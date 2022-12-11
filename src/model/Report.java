package model;

public class Report {

    private final String status;
    private final Boolean success;

    public Report(String status, Boolean success) {
        this.status = status;
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public Boolean isSuccess() {
        return success;
    }

}
