/**
 * Created by user on 10/26/2018.
 */
public class totientSieve {
    //calculate totient values from 1 to phiValues.length-1
    public totientSieve(int[] phiValues){
        for (int i = 1; i < phiValues.length; i++)
            phiValues[i] = i;

        for (int p = 2; p <phiValues.length; p++) {
            //p is prime
            if (phiValues[p] == p) {
                phiValues[p] = p - 1;
                // Update multiples of p
                for (int i = 2 * p; i < phiValues.length; i += p) {
                    phiValues[i] = (phiValues[i] / p) * (p - 1);
                }
            }
        }
    }
}
