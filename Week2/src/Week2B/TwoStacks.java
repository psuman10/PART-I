package Week2B;

public class TwoStacks {
    
    
    
    public int popCount(int k, int[] stka, int[] stkb) {
 
        int stka_index = 0;
        int stkb_index = 0;
        int count = 0;
        int sum = 0;
        
        
        while (stkb_index < stkb.length && sum + stkb[stkb_index] < k) {
            sum += stkb[stkb_index];
            stkb_index++;
        }
        
        stkb_index--;
        count = stkb_index + 1;
        
        
        while (stka_index < stka.length && stkb_index < stkb.length) {
            sum += stka[stka_index];
            
            if (sum >= k) {
                
                while (stkb_index >= 0) {
                    sum -= stkb[stkb_index];
                    stkb_index--;
                    if (sum < k) {
                        break;
                    }
                }
                
                if (sum >= k && stkb_index < 0) {
                    stka_index--;
                    break;
                }
            }
              
            count = Math.max(stka_index + stkb_index + 2, count);
            stka_index++;
        }
 
        return count;
    }
    public static void main(String[] args) {
         
        int k = 11;
        
        int stka[] = {4, 3, 6, 7, 9};
        int stkb[] = {1, 2, 9, 5};
        
        TwoStacks mp_obj = new TwoStacks();
         
        int max_count = mp_obj.popCount(k, stka, stkb);
        System.out.println("Result: " + max_count);
        
    }
}