package me.syncify.unittestandroid;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by adarshpandey on 9/12/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ArgumentCaptorTest {
    private CarSelector carSelector;

    @Mock
    private CarSelector.CarBuilderService service;

    @Before
    public void initTest(){
        carSelector = new CarSelector(service);
    }

    @Test
    public void testBuyRedFerrari() {

        carSelector.buyRedFerrari();

        ArgumentCaptor<CarSelector.BuyingCriteria> argumentCaptor = ArgumentCaptor.forClass(CarSelector.BuyingCriteria.class);
        Mockito.verify(service).buildCar(argumentCaptor.capture());
        CarSelector.BuyingCriteria buyingCriteria = argumentCaptor.getValue();

        Assert.assertEquals("RED", buyingCriteria.getColor());
        Assert.assertEquals("FERRARI", buyingCriteria.getModel());

    }
}
