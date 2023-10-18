package ua.foxminded.javaspring.kocherga.web_application.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ua.foxminded.javaspring.kocherga.web_application.models.Schedule;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource(properties = "spring.jpa.hibernate.ddl-auto=validate")
class ScheduleRepositoryTest {

    @Autowired
    private ScheduleRepository scheduleRepository;

    //ToDo I don't know the correct way to test custom Queries.
    // Is it something similar to what I wrote below?
    // Do I need to add data to the db before the main test and
    // then compare whether they are in the list?
    @Test
    void findScheduleInDateRange() {
        String yearMonth = "2023-10";

        YearMonth ym = YearMonth.parse(yearMonth);
        LocalDate startDate = ym.atDay(1);
        LocalDate endDate = ym.atEndOfMonth();

        List<Schedule> scheduleList = scheduleRepository.findScheduleInDateRange(startDate, endDate);

        assertNotNull(scheduleList);
        assertFalse(scheduleList.isEmpty());
    }
}
