import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Test {
	private static final int[] DIGITAL_WEIGHT = {5,9,7,8,4,2,1,9,3,7,6,5,8,4,6,2,5,9,5,2,3,9,7,1,2,9};
	
	/** 
	 * @Title: main 
	 * @Description: TODO 
	 * @param args
	 * @return void
	 * @throws 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(signPosition("82972080501533"));
	}
	 /**
     * 生成较验位
     * @param num
     * @return
     */
    public static int signPosition(String num){
    	Pattern pattern = Pattern.compile("^\\d+$");
    	Matcher matcher =pattern.matcher(num);
    	boolean bool = matcher.matches();
    	if(!bool)
    		 throw new IllegalArgumentException("传入的参数不正确");
    	char[] digital =num.toCharArray();
    	int count=0;
    	for(int i=0;i<digital.length;i++){
    		count+=Integer.parseInt(num.charAt(i)+"")*DIGITAL_WEIGHT[i];
    		System.out.println(i+"    "+num.charAt(i)+"    "+DIGITAL_WEIGHT[i]+"   "+Integer.parseInt(num.charAt(i)+"")*DIGITAL_WEIGHT[i]);
    	}
    	return count%10;
    }
}
