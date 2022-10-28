package microservices.book.gamification.service;

import microservices.book.gamification.domain.LeaderBoardRow;
import microservices.book.gamification.repository.ScoreCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

public class LeaderBoardServiceImplTest {
    @Mock
    private ScoreCardRepository scoreCardRepository;

    private LeaderBoardServiceImpl leaderBoardService;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);

        leaderBoardService = new LeaderBoardServiceImpl(scoreCardRepository);

    }

    @Test
    public void retrieveLeaderBoardTest() {
        // given
        Long userId = 1L;
        LeaderBoardRow leaderRow1 = new LeaderBoardRow(userId, 2200L);
        List<LeaderBoardRow> expectedLeaderBoard = Collections.singletonList(leaderRow1);
        given(scoreCardRepository.findFirst10()).willReturn(expectedLeaderBoard);

        // when
        List<LeaderBoardRow> leaderBoard = leaderBoardService.getCurrentLeaderBoard();

        // then
        assertThat(leaderBoard).isEqualTo(expectedLeaderBoard);
    }

    @Test
    public void getCurrentLeaderBoardTest() {
        //given
        given(scoreCardRepository.findFirst10()).willReturn(get10LeadersBoardRow());

        //When
        List<LeaderBoardRow> leaderBoardRowList = leaderBoardService.getCurrentLeaderBoard();

        //Verify
        assertThat(leaderBoardRowList.size()).isEqualTo(get10LeadersBoardRow().size());
        //assertThat(leaderBoardRowList).isEqualTo(Collections.emptyList());

        verify(scoreCardRepository).findFirst10();
    }


    private List<LeaderBoardRow> get10LeadersBoardRow() {

        List<LeaderBoardRow> leaders = new ArrayList<>();

        LeaderBoardRow leaderBoardRow1 = new LeaderBoardRow(1L, 2200L);
        LeaderBoardRow leaderBoardRow2 = new LeaderBoardRow(1L, 2000L);
        LeaderBoardRow leaderBoardRow3 = new LeaderBoardRow(1L, 1900L);
        LeaderBoardRow leaderBoardRow4 = new LeaderBoardRow(1L, 1800L);
        LeaderBoardRow leaderBoardRow5 = new LeaderBoardRow(1L, 1500L);
        LeaderBoardRow leaderBoardRow6 = new LeaderBoardRow(1L, 1000L);
        LeaderBoardRow leaderBoardRow7 = new LeaderBoardRow(1L, 900L);
        LeaderBoardRow leaderBoardRow8 = new LeaderBoardRow(1L, 800L);
        LeaderBoardRow leaderBoardRow9 = new LeaderBoardRow(1L, 700L);
        LeaderBoardRow leaderBoardRow10 = new LeaderBoardRow(1L, 600L);
        LeaderBoardRow leaderBoardRow11 = new LeaderBoardRow(1L, 500L);

       leaders.add(leaderBoardRow1);
       leaders.add(leaderBoardRow2);
       leaders.add(leaderBoardRow3);
       leaders.add(leaderBoardRow4);
       leaders.add(leaderBoardRow5);
       leaders.add(leaderBoardRow6);
       leaders.add(leaderBoardRow7);
       leaders.add(leaderBoardRow8);
       leaders.add(leaderBoardRow9);
       leaders.add(leaderBoardRow10);
        leaders.add(leaderBoardRow11);

       return  leaders;
    }
}
