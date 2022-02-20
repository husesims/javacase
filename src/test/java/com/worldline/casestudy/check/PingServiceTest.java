package com.worldline.casestudy.check;

//import org.easymock.EasyMockExtension;
//import org.easymock.EasyMockSupport;
//import org.easymock.Mock;
//import org.easymock.TestSubject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import java.io.IOException;



import static org.assertj.core.api.Assertions.assertThat;
//import static org.easymock.EasyMock.expect;

class PingServiceTest {

    @BeforeEach
    void setUp() {
        pingService=new PingService();
    }

    @AfterEach
    void tearDown() {
    }


    private PingService pingService;
            //partialMockBuilder(PingService.class)
            //.addMockedMethod("getConnection", String.class)
            //.mock();

    @Test
    void isValidURL_valid_test() {
        assertThat(pingService.isValidURL("http://google.com")).isEqualTo(true);
    }
    @Test
    void isValidURL_imvalid_test() {
        assertThat(pingService.isValidURL("htt://google.com")).isEqualTo(false);
    }
    @Test
    void isValidURL_empty_string() {
        assertThat(pingService.isValidURL("")).isEqualTo(false);
    }
    @Test
    void isOK() throws IOException {
        //I tried to use easymock to mock private method, unfortunately, as far as I read from their github repo, they have problems with Java 17, i didnot want to decrease the JDK version.
        /*
        HttpURLConnection httpConn = mock(HttpURLConnection.class);
        expect(httpConn.getResponseCode()).andReturn(200);
        expect(pingService.getConnection("test")).andReturn(httpConn);

        replayAll();

        assertThat(pingService.isOK("test", 200) ).isEqualTo(false);
*/



    }
}