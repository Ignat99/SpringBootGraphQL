package com.ochiengolanga.tuts.bootgraphql.domain.entity;

//import com.ochiengolanga.tuts.bootgraphql.exceptions.FeedDateException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//import java.math.BigDecimal;

//import static java.math.BigDecimal.ONE;
//import static java.math.BigDecimal.TEN;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class FeedTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getFormattedDateThrowFeedDateExceptionWhenpubDateIsGoingStandart() {
        final Feed feed = Feed.builder().title("Dummy").description("bla bla bla").pubDate("Sun, 13 Dec 2020 16:05:36 +0100").build();

//        thrown.expect(FeedDateException.class);

        System.out.printf(feed.getFormattedDate());

        assertThat(feed.getFormattedDate()).isEqualTo("Sun, 13 Dec 2020 16:05:36 +0100");
    }
}