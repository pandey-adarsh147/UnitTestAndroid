package me.syncify.unittestandroid;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import rx.Observable;
import rx.Scheduler;
import rx.observers.TestSubscriber;
import rx.plugins.RxJavaSchedulersHook;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by adarshpandey on 9/4/17.
 */

public class GithubAPIUnitTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public PrintRules printRules = new PrintRules();

    @Mock
    GithubAPI githubAPI;

    RxSchedulersAbs rxSchedulersAbs;

    @Mock
    PrinterService printerService;

    @InjectMocks
    Presenter presenter;

    @Before
    public void setup() {
//        githubAPI = mock(GithubAPI.class);
        rxSchedulersAbs = new RxSchedulersTest();
    }

    @Test
    public void testRxJava() {
        when(githubAPI.getGithubDto()).thenReturn(Observable.just(new GithubDto(2, "Hello")));
        when(printerService.print()).thenReturn("ABCD");

        TestSubscriber<GithubDto> testSubscriber = new TestSubscriber<>();

        githubAPI.getGithubDto()
                .subscribe(testSubscriber);

        testSubscriber.awaitTerminalEvent();
        // test no errors was not occurred
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();

        GithubDto personalFullDataModel = testSubscriber.getOnNextEvents().get(0);


        assert personalFullDataModel.id == 2;

        presenter.printer().equals("ABCD");

        verify(printerService).print();
    }

    private class SchedulerHook extends RxJavaSchedulersHook {
        @Override
        public Scheduler getIOScheduler() {
            return Schedulers.immediate();
        }

        @Override
        public Scheduler getNewThreadScheduler() {
            return Schedulers.immediate();
        }

        @Override
        public Scheduler getComputationScheduler() {
            return Schedulers.immediate();
        }
    }
}
