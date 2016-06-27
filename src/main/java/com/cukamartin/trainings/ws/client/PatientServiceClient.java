package com.cukamartin.trainings.ws.client;

import com.bharaththippireddy.trainings.jaxrs.Patient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PatientServiceClient {

    private static final String PATIENT_SERVICE_URL = "http://localhost:8080/restws/services/patientservice";

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();

        Patient patient = getPatient(client);
        updatePatient(client, patient);
        getPatient(client);
        createPatient(client);
        deletePatient(client);

        client.close();
    }

    /**
     * Vymaze pacienta z ID 124
     */
    private static void deletePatient(Client client) {
        WebTarget deleteTarget = client.target(PATIENT_SERVICE_URL).path("/patients").path("/{id}")
                .resolveTemplate("id", 124);

        Response deleteResponse = deleteTarget.request().delete();

        System.out.println("HTTP STATUS DELETE: " + deleteResponse.getStatus());

        deleteResponse.close();
    }

    /**
     * Vrati pacienta s id 123 a vypise na konzolu status a jeho meno
     */
    private static Patient getPatient(Client client) {
        WebTarget getTarget = client.target(PATIENT_SERVICE_URL).path("/patients").path("/{id}")
                .resolveTemplate("id", 123);

        Response response = getTarget.request().get();
        Patient patient = getTarget.request().get(Patient.class);

        System.out.println("HTTP STATUS GET: " + response.getStatus());
        System.out.println("Patient name: " + patient.getName());

        response.close();

        return patient;
    }

    /**
     * updatne meno pacienta
     */
    private static void updatePatient(Client client, Patient patient) {
        WebTarget putTarget = client.target(PATIENT_SERVICE_URL).path("/patients");

        patient.setName("John Smith");

        // co chceme updatnut a v akom formate...
        Response updateResponse = putTarget.request().put(Entity.entity(patient, MediaType.APPLICATION_XML));
        System.out.println("HTTP STATUS PUT " + updateResponse.getStatus());

        updateResponse.close();
    }

    /**
     * Vytvori noveho pacienta.
     */
    private static void createPatient(Client client) {
        Patient newPatient = new Patient();
        newPatient.setName("Martin");

        WebTarget postTarget = client.target(PATIENT_SERVICE_URL).path("/patients");
        // co chceme vytvorit, v akom formate, co ma vratit (narozdiel od updatu vracia Pacienta, response je default)
        Patient responsePatient = postTarget.request().post(Entity.entity(newPatient, MediaType.APPLICATION_XML), Patient.class);

        System.out.println("New Patient: " + responsePatient.getId() + " " + responsePatient.getName());
    }
}
