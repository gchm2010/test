package com.dc.radom;

import java.text.DecimalFormat;
import java.util.Hashtable;
import java.util.Random;

public class RadomUtil {

	// �趨��λ��

	private static final int LENGTH = 8;

	/**
	 * ���ǵ��͵����ϴ���㷨�� �����Ǵӱ�ѡ������ѡ��һ������Ŀ�������У���ѡȡ������ӱ�ѡ�����Ƴ���������󣬲���Сѡ������ �㷨ʱ�临�Ӷ�O��n��
	 * 
	 * @return ���8Ϊ���ظ�����
	 */
	public static String generateNumber() {
		String no = "";
		// ��ʼ����ѡ����
		int[] defaultNums = new int[10];
		for (int i = 0; i < defaultNums.length; i++) {
			defaultNums[i] = i;
		}
		Random random = new Random();
		int[] nums = new int[LENGTH];
		// Ĭ�������п���ѡ��Ĳ��ֳ���
		int canBeUsed = 10;
		// ���Ŀ������
		for (int i = 0; i < nums.length; i++) {
			// �����ѡȡ�����ִ���Ŀ������
			int index = random.nextInt(canBeUsed);
			nums[i] = defaultNums[index];
			// �����ù��������ӵ���ѡ������󣬲���С��ѡ����
			swap(index, canBeUsed - 1, defaultNums);
			canBeUsed--;
		}

		if (nums.length > 0) {
			for (int i = 0; i < nums.length; i++) {
				no += nums[i];
			}
		}
		return no;
	}

	/**
	 * ��������
	 * 
	 * @param i
	 *            ����λ��
	 * @param j
	 *            ������λ��
	 * @param nums
	 *            ����
	 */
	private static void swap(int i, int j, int[] nums) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	/**
	 * ��ȡ8λ��
	 * 
	 * @return
	 */
	public static String generateNumber2() {
		String no = "";
		int num[] = new int[8];
		int c = 0;
		for (int i = 0; i < 8; i++) {
			num[i] = new Random().nextInt(10);
			c = num[i];
			for (int j = 0; j < i; j++) {
				if (num[j] == c) {
					i--;
					break;
				}
			}
		}

		if (num.length > 0) {
			for (int i = 0; i < num.length; i++) {
				no += num[i];
			}
		}
		return no;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws InterruptedException {
		// long s = System.currentTimeMillis();
		// for(int i =0;i<100000000;i++){
		// generateNumber2();
		// }
		// long e = System.currentTimeMillis();
		// System.out.println(e-s);
		// s = System.currentTimeMillis();
		// for(int i =0;i<100000000;i++){
		// generateNumber();
		// }
		// e = System.currentTimeMillis();
		// System.out.println(e-s);
//		for(int i=0;i<1000;i++){
			testRandom();
//			Thread.sleep(200);
//		}
	}

	private static void testRandom() throws InterruptedException {
		Random rand = new Random();
		rand.setSeed(rand.nextInt(9999));
		DecimalFormat df = new DecimalFormat();
		df.applyLocalizedPattern("00000000");
		System.out.println(df.format(rand.nextInt(99999999)));
		/**int length = 30000, num = 0, numP = 0, j = 0,first = length;
		boolean isD = false;

		j = 0;
		Hashtable tab = new Hashtable(length);
		for (int i = 0; i < length; i++) {
			do {
				j++;
//				 num = (rand.nextInt(99) * 1000000 + (int) (System.currentTimeMillis() % 10000) * 100 + rand.nextInt(99));
//				 num = (rand.nextInt(9999) * 10000  + rand.nextInt(9999) * 100 + rand.nextInt(99));
//				 num = rand.nextInt(999999) * 1000000  + rand.nextInt(99);
				num = ((int) (System.currentTimeMillis()% 10000) * 10000 + rand.nextInt(9999));
//				num = ((int) (System.currentTimeMillis() * rand.nextInt(9)* rand.nextInt(9) % 10000) * 10000 + rand.nextInt(9999));
			} while (tab.containsKey(num));
			tab.put(num, 0);
//			System.out.print(numP - num + "\t");
//			numP = num;
//			if (i % 5 == 4)
//				System.out.println();
//			if (j % 1000 == 0)
//				System.out.println(i+"\t"+j);
			if((i+1) != j && !isD){
				first = j;
				isD = true;
				//break;
			}
			 Thread.sleep(1);//rand.nextInt(1)
		}
		System.out.println(j-length + "\t" + first);
//		System.out.println("\t\t"+j);**/
	}
}