import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hospital {
    private List<Patient> patientList;
    private String location;

    public Hospital(String location) {
        patientList = new ArrayList<>();
        this.location = location;
    }

    public List<Patient> addPatient(Patient... patient) {
        patientList.addAll(Arrays.asList(patient));
        return patientList;
    }

    public String getHospitalLocation() {
        return this.location;
    }

    public List<Patient> getPatientsList() {
        return patientList;
    }

    public long getTotalPatientsCount(LocalDate startDate, LocalDate endDate) {
        return patientList.stream().filter(patient -> patient.isPatientVisitsInDateRange(startDate, endDate)).count();
    }

    public long getLocalPatientsCount(LocalDate startDate, LocalDate endDate) {
        return patientList.stream().filter(patient -> patient.getLocationOfPatient().equals(this.location)
                && patient.isPatientVisitsInDateRange(startDate, endDate)).count();

    }

    public long getOutStationPatientCount(LocalDate startDate, LocalDate endDate) {
        return getTotalPatientsCount(startDate, endDate) - getLocalPatientsCount(startDate, endDate);
    }

    public double getLocalPatientPercentage(LocalDate startDate, LocalDate endDate) {
        double percentage = 0.0;
        double total = getTotalPatientsCount(startDate, endDate);
        if (total != 0.0)
            percentage = getLocalPatientsCount(startDate, endDate) * 100 / total;
        DecimalFormat df = new DecimalFormat("#.##");
        percentage = Double.valueOf(df.format(percentage));
        return percentage;

    }

    public double getLOutStationPatientPercentage(LocalDate startDate, LocalDate endDate) {
        double percentage = 0.0;
        double total = getTotalPatientsCount(startDate, endDate);
        if (total != 0.0)
            percentage = getOutStationPatientCount(startDate, endDate) * 100 / total;
        DecimalFormat df = new DecimalFormat("#.##");
        percentage = Double.valueOf(df.format(percentage));
        return percentage;

    }

}
