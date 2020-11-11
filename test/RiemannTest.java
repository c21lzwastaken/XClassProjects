import com.LucasZhang.Riemann.RightHandRule;
import org.dalton.polyfun.Polynomial;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RiemannTest {
    Polynomial vx = new Polynomial(new double[]{0, 2});
    Polynomial fx = new Polynomial(new double[]{0, 0, 1});

    RightHandRule rh = new RightHandRule();

    @Test
    public void slice1() {
        assertThat(rh.slice(vx, 0, 1), is(2.0));
    }
    @Test
    public void slice2(){
        assertThat(rh.slice(vx, 1, 2), is(4.0));
    }
    @Test
    public void slice3(){
        assertThat(rh.slice(vx, 0, 2), is(8.0));
    }
    @Test
    public void slice4(){
        assertThat(rh.slice(fx, 4, 5), is(25.0));
    }
    @Test
    public void slice5(){
        assertThat(rh.slice(fx, 4.5, 6), is(54.0));
    }

    @Test
    public void sum1(){
        assertThat(rh.sum(vx, 0, 4, 8), is(18.0));
    }
    @Test
    public void sum2(){
        assertThat(rh.sum(vx, 0, 4, 16), is(17.0));
    }
    @Test
    public void sum3(){
        assertThat(rh.sum(fx, 0, 4, 4), is(30.0));
    }
}
