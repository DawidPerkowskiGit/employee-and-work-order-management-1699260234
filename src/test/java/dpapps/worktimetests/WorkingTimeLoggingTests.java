package dpapps.worktimetests;

import dpapps.model.User;
import dpapps.model.WorkingLog;
import dpapps.model.repository.service.UserService;
import dpapps.model.repository.service.WorkingLogService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@SpringBootTest
@AutoConfigureMockMvc
public class WorkingTimeLoggingTests {

    private final UserService userService;

    private final String paramStartDate = "startDate";

    private final String paramStartTime = "startTime";

    private final String paramEndDate = "endDate";

    private final String paramEndTime = "endTime";

    private final String paramUser = "user.id";

    private final String[] userData = {"testlogin", "testpassword", "testemail"};

    private final User user = new User(userData[0], userData[1], userData[2]);

    @Autowired
    private MockMvc mockMvc;

    private final WorkingLogService workingLogService;

    @Autowired
    public WorkingTimeLoggingTests(UserService userService, WorkingLogService workingLogService) {
        this.userService = userService;
        this.workingLogService = workingLogService;
    }

    @BeforeEach
    public void addUserToPerformTests() {
        addTestingUser();
    }

    @AfterEach
    public void removeUserAfterTests() {
        removeTestingUser();
    }

    @Test
    @WithMockUser(username = "testlogin")
    public void testStartAndStopWorkTimeLogging() throws Exception {
        User exisitngUser = userService.getAuthenticatedUser();

        WorkingLog workingLogBeforeStart = workingLogService.findLatestEntry(exisitngUser);

        mockMvc.perform(post("/worktime/start")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(redirectedUrl("/worktime?startSuccess"));


        WorkingLog workingLogAfterStart = workingLogService.findLatestEntry(exisitngUser);

        mockMvc.perform(post("/worktime/stop")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(redirectedUrl("/worktime?stopSuccess"));

        WorkingLog workingLogAfterStop = workingLogService.findLatestEntry(exisitngUser);

        workingLogService.removeAllByUser(exisitngUser);

        assertTrue(workingLogBeforeStart.getId() == null &&
                workingLogAfterStart.isClockedIn() == true &&
                workingLogAfterStart.getStartTime() != null &&
                workingLogAfterStart.getStartDate() != null &&
                workingLogAfterStart.getEndTime() == null &&
                workingLogAfterStart.getEndDate() == null &&
                workingLogAfterStop.isClockedIn() == false &&
                workingLogAfterStop.getStartTime() != null &&
                workingLogAfterStop.getStartDate() != null &&
                workingLogAfterStop.getEndTime() != null &&
                workingLogAfterStop.getEndDate() != null
        );
    }

    private String removeTestingUser() {
        try {

            return userService.delete(userData[0]);
        } catch (Exception e) {
            System.out.println("could not remove testing user");
            e.printStackTrace();
        }
        return "";
    }

    private String addTestingUser() {
        return userService.add(user);
    }
}
