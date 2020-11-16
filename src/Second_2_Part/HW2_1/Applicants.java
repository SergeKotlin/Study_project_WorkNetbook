package Second_2_Part.HW2_1;

import java.util.ArrayList;
import java.util.List;

public class Applicants {
    private Integer[] idOfApplicants;

    Applicants(Integer[] idOfApplicants) {
        this.idOfApplicants = idOfApplicants;
    }

    public void setIdOfApplicants(Integer[] idOfApplicants) {
        this.idOfApplicants = idOfApplicants;
    }

    public Integer[] getIdOfApplicants() {
        return idOfApplicants;
    }
}
