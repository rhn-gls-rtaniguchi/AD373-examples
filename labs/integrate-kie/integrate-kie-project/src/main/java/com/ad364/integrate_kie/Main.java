package com.ad364.integrate_kie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ad364.bc_tables.OperatingSystem;
import com.ad364.bc_tables.Recommendation;
import com.ad364.bc_tables.TravelProfile;
import org.kie.api.KieServices;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ExecutionResults;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.CredentialsProvider;
import org.kie.server.client.KieServicesClient;
import org.kie.server.client.KieServicesConfiguration;
import org.kie.server.client.KieServicesFactory;
import org.kie.server.client.RuleServicesClient;
import org.kie.server.client.credentials.EnteredCredentialsProvider;

public class Main {

    private static final String KIE_SERVER_URL = "http://localhost:8080/kie-server/services/rest/server";
    private static final String USERNAME = "kieserver";
    private static final String PASSWORD = "kieserver1!";
    private static final String CONTAINER_ID = "integrate-kie";

    public static void main(String[] args) {
        CredentialsProvider credentialsProvider = new EnteredCredentialsProvider(USERNAME, PASSWORD);
        KieServicesConfiguration kieServicesConfig = KieServicesFactory.newRestConfiguration(KIE_SERVER_URL, credentialsProvider);
        addExtraClasses(kieServicesConfig);
        kieServicesConfig.setMarshallingFormat(MarshallingFormat.JSON);

        KieServicesClient kieServicesClient = KieServicesFactory.newKieServicesClient(kieServicesConfig);
        RuleServicesClient rulesClient = kieServicesClient.getServicesClient(RuleServicesClient.class);

        List<Command<?>> commands = new ArrayList<>();
        KieServices kieServices = KieServices.Factory.get();
        KieCommands commandFactory = kieServices.getCommands();
        commands.add(commandFactory.newInsert(getOperatingSystem("macOS")));
        commands.add(commandFactory.newInsert(getTravelProfile(35, false)));
        commands.add(commandFactory.newFireAllRules());
        commands.add(commandFactory.newGetObjects("objects"));
        commands.add(commandFactory.newDispose());

        BatchExecutionCommand batchExecutionCommand = commandFactory.newBatchExecution(commands);

        ServiceResponse<ExecutionResults> response = rulesClient.executeCommandsWithResults(CONTAINER_ID, batchExecutionCommand);
        ExecutionResults results = response.getResult();
        System.out.println(results.getValue("objects"));

    }

    /**
     * Adds classes for marshalling/unmarshalling.
     */
    private static void addExtraClasses(KieServicesConfiguration connectionConfig) {
        Set<Class<?>> extraClassList = new HashSet<>();
        extraClassList.add(OperatingSystem.class);
        extraClassList.add(Recommendation.class);
        extraClassList.add(TravelProfile.class);
        connectionConfig.addExtraClasses(extraClassList);
    }

    private static OperatingSystem getOperatingSystem(String name) {
        OperatingSystem operatingSystem = new OperatingSystem();
        operatingSystem.setOsName(name);
        return operatingSystem;
    }

    private static TravelProfile getTravelProfile(int travelPercent, boolean noTravel) {
        TravelProfile travelProfile = new TravelProfile();
        travelProfile.setTravelPercent(travelPercent);
        travelProfile.setNoTravel(noTravel);
        return travelProfile;
    }

}
