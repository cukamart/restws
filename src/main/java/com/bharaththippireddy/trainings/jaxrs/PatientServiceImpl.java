package com.bharaththippireddy.trainings.jaxrs;

import com.bharaththippireddy.trainings.jaxrs.exceptions.SomeBusinessException;

import javax.jws.WebService;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@WebService(name = "PatientServiceImpl")
public class PatientServiceImpl implements PatientService {
    private long currentId = 123;
    private Map<Long, Patient> patients = new HashMap<>();
    private Map<Long, Prescription> prescriptions = new HashMap<>();

    public PatientServiceImpl() {
        init();
    }

    private void init() {
        Patient patient = new Patient();
        patient.setName("John");
        patient.setId(currentId);
        patients.put(patient.getId(), patient);

        Prescription prescription = new Prescription();
        prescription.setDescription("prescription 223");
        prescription.setId(223);
        prescriptions.put(prescription.getId(), prescription);
    }

    public Response addPatient(Patient patient) {
        System.out.println("... invoking addPatient, Patient Name is " + patient.getName());

        patient.setId(++currentId);
        patients.put(patient.getId(), patient);

        return Response.ok(patient).build();
    }

    public Response updatePatient(Patient updatedPatient) {
        System.out.println("... invoking updatePatient, Patient Name is " + updatedPatient.getName());

        Patient currentPatient = patients.get(updatedPatient.getId());

        if (currentPatient != null) {

            if (currentPatient.getName().equals(updatedPatient.getName())) {
                return Response.notModified().build();
            }

            patients.put(updatedPatient.getId(), updatedPatient);

            System.out.println("Updated patient name is: " + patients.get(updatedPatient.getId()).getName());

            return Response.ok(true).build();
        } else {
            throw new NotFoundException();
        }
    }

    public Response deletePatients(String id) {
        System.out.println("... invoking deletePatient, Patient Id is " + id);

        Patient currentPatient = patients.get(Long.parseLong(id));

        if (currentPatient != null) {
            patients.remove(currentPatient.getId());

            return Response.ok(true).build();
        } else {
            throw new SomeBusinessException("Business Exception");
        }
    }

    public Patient getPatient(String id) {
        System.out.println("... invoking getPatient, Patient Id is " + id);

        long idNumber = Long.parseLong(id);

        Patient patient = patients.get(idNumber);

        if (patient == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return patients.get(Long.parseLong(id));
    }

    public Prescription getPrescription(String prescriptionId) {
        return prescriptions.get(Long.parseLong(prescriptionId));
    }

}
