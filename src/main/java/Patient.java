import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Patient {
    private String fullName;
    private String location;
    private List<VisitDate> dateOfVisits;

    public Patient(String fullName, String location) {
        this.fullName = fullName;
        this.location = location;
        dateOfVisits = new ArrayList<>();
    }

    public void addVisits(VisitDate... visit) {
        dateOfVisits.addAll(Arrays.asList(visit));
    }

    public String getPatientName() {
        return this.fullName;
    }

    public String getLocationOfPatient() {
        return this.location;
    }

    private List<LocalDate> getDateOfVisits() {
        return dateOfVisits.stream().map(visitDate -> visitDate.getDate()).collect(Collectors.toList());
    }

    public boolean isPatientVisitsInDateRange(LocalDate startDate, LocalDate endDate) {
        if (getDateOfVisits().stream().filter(visitDate ->
                visitDate.isAfter(startDate) && visitDate.isBefore(endDate)).count() > 0)
            return true;
        else
            return false;
    }
}
