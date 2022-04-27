package Test220426;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestReceipt_3 {

	public static void main(String[] args) {
		// ������ ��� 3 , �̸�Ʈ

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat form = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		String nowTime = form.format(cal.getTime());
		SimpleDateFormat form2 = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String nowTime2 = form2.format(cal.getTime());
		SimpleDateFormat form3 = new SimpleDateFormat("YYYYMMdd");
		String nowTime3 = form3.format(cal.getTime());

		DecimalFormat df = new DecimalFormat("###,###,###,###,###");

		String[] k11_itemName = { "����ǰ(��) �Ҵ�«�� 140g*4��" , "[�õ�][�±�] ��귣�� �õ� ����� (31-40) (450g)", "[��������] ���� �̿� 150g",
				"GAP ������ 4~6��/�� (1.3kg����)", "[CJ] ��޳ʰ� 550g", "[CJ] ����ձ���1.12kg", "�Ϸ�Ʈ�θ� UHD TV(E55KL7701)",
				"[�������] ������� 2.3L", "��귣�� �����Ѱ���ġ 1.5kg", "[����ũ] ������� �丮��� 870g", "[����ũX�����] ������� �Ľ�Ÿ 679g",
				"��ǥ �����Ͻ� �䵵�� ���߸��������� 150g", "G)����ũ ����Ĩ��Ű Ŭ���� 150g", "��귣�� ����Ĩ �������� 110g", "ũ��� ��������� 372g(12��)",
				"[����] Ȩ�������� 5���� 230g", "û��)�˵�����Ĩ240g", "�Ե� ī��Ÿ�� 2���� 460G(20��)", "������ ����������Ĩ 12P+4P 320G",
				"ģȯ�� ���� ���� ���� 1kg", "ũ���ؽ� 3�� ����&����Ʈ 30m*30��", "�޵�� ġ��ġ�� �������� 120G*3��", "[����ũ] �̴�ũ�οͻ�300g(����)",
				"��귣�� �ǳӹ��� ũ���� 340g", "�︳ �����㸸��400g", "������ 1.5kg", "CJ�鼳�Ž�û310ml", "���ô�����ƾ 14�� ��������",
				"��귣�� ������ �ᳪ�� 300g", "��ô���(��)", "���ѱ� ����� �ſ�� 120g*5��" };
		int[] k11_price = { 4480, 12800, 4280, 7900, 8980, 9980, 15490000, 6350, 11780, 9980, 13800, 2980, 1480, 890,
				3840, 5980, 4980, 6980, 3840, 3480, 23478, 9900, 5980, 2980, 3980, 12800, 4980, 12800, 1280, 3980,
				3100 };
		int[] k11_amount = { 5, 1, 3, 1, 2, 4, 2, 1, 5, 1, 2, 1, 5, 8, 15, 2, 3, 3, 4, 1, 1, 10, 3, 3, 1, 1, 2, 1, 2, 6,
				2 };
		// �鼼 ��ǰ�� : �̰����ķ�ǰ ��(�Ŀ����� �����Ǵ� ��,��,��,�ӻ깰�� ����)
		boolean[] k11_taxFree = { false, false, true, true, false, false, false, true, false, false, false, false,
				false, false, false, false, false, false, false, true, false, false, false, false, false, false, false,
				false, true, true, false };

		for (int i = 0; i < k11_itemName.length; i++) { // ��ǰ�� ��ȯ
			
			if (k11_taxFree[i] == true) { // ���� �鼼 ��ǰ�̶��?
				k11_itemName[i] = "* " + k11_itemName[i]; // �տ��ٰ� *�� �ٿ��ش�.
			} else { // �鼼 ��ǰ�� �ƴ϶�� k11_taxFree[i] == false
				k11_itemName[i] = "  " + k11_itemName[i]; // �տ��ٰ� ��ĭ�� �ٿ��ش�.
			}
			
			if(k11_itemName[i].getBytes().length <= 15) { // ��ǰ���� byte�� 15���϶�� 
				while (k11_itemName[i].getBytes().length < 15) { // ��ǰ���� byte�� 15�� �ɶ����� �ݺ�
					k11_itemName[i] += " "; // ��ǰ���� byte �� 15�� �ɶ����� ��ĭ�� �ٿ��ش�.
				}
			} else { // ��ǰ���� byte�� 15 �ʰ���� 
				//��ǰ���� 15byte ��ŭ �߶� �����Ѵ�.
				//������ 15byte �κ��� �ѱ��̶�� �������ڰ� ���� �ȴ�.
				//���� �ѱ��ھ� �˻��ϸ鼭 �����ְ� ���ڰ� ���������� 15byte�� �ʰ��ȴٸ� ���ڸ� �ȴ����ְԲ� ����

				int k11_byteCount = 0;
				String k11_newName = "";
				for(char ch : k11_itemName[i].toCharArray()) { //��ǰ���� char ������ �ѱ��ھ� �ɰ��� Array�� ����� �ش�. -> char ch
					
					k11_byteCount += String.valueOf(ch).getBytes().length; //�ѱ���(char)�� byte���̸� ��� �� k11_byteCount �� �־��ش�.
					if(k11_byteCount > 15) //���� k11_byteCount �� 15�� �Ѿ ������ 
						break; // break�� ���־ �ڿ� ���� ��ġ�� ������ ���� �ʰ� ���ش�.
					k11_newName += ch; // byte�� 15�� �ɶ����� ��ģ��.

				}

				k11_itemName[i] = k11_newName; // �˸°� �ڸ� ��ǰ���� �ٽ� ���� ����(k11_itemName[i])�� �־��ش�.
				// 15byte �κп� �ѱ��� �ִ� ��ǰ���� �� �տ��� �߶������Ƿ� ��ǰ���� byte�� 15byte���� �������̴�.
				if(k11_itemName[i].getBytes().length <= 15) { // �׷��� 15byte ���� ���ٸ�
					while (k11_itemName[i].getBytes().length < 15) {
						k11_itemName[i] += " "; // 15byte�� �ɶ����� ��ĭ�� �ٿ��ִ°��� �ݺ� 
					}
				}
			}
				
		}

		
		
		int k11_sumTaxFree = 0;
		int k11_sumPrice = 0;
		

		System.out.println(" emart       �̸�Ʈ ������ (031)888-1234");
		System.out.println(" emart       206-86-50913 ����");
		System.out.println(" emart       ���� ������ ������� 552");
		System.out.println("������ �������� ��ȯ/ȯ�� �Ұ�");
		System.out.println("�����ǰ�� ����, 30�� �̳�(�ż� 7��)");
		System.out.println("�� �Ϻ� �귣����� ����(���� ����������)");
		System.out.println("��ȯ/ȯ�� ���������� ����(����ī�� ����)\n");
		System.out.printf("[����] %s    pos:%s\n", nowTime, "0011-9861");
		System.out.println("----------------------------------------");

		System.out.printf("%6.10s%16.4s%3.2s%6.8s\n", "�� ǰ ��", "�� ��", "����", "�� ��");
		System.out.println("----------------------------------------");

		for (int index = 0; index < k11_itemName.length; index++) {
			System.out.printf("%6s%11.10s%3.2s%11.10s\n", k11_itemName[index], df.format(k11_price[index]),
					df.format(k11_amount[index]), df.format(k11_price[index] * k11_amount[index]));
			
			if(k11_taxFree[index] == true) {
				k11_sumTaxFree += k11_price[index] * k11_amount[index];
			} else {
				k11_sumPrice += k11_price[index] * k11_amount[index];
			}
			

			if ((index + 1) % 5 == 0) {
				System.out.println("----------------------------------------");
			}
		}
		
		int k11_VAT = 0;
		if( k11_sumPrice != 0) {
			k11_VAT = k11_sumPrice / 11 + 1;
		}
		
		
		
		System.out.println();
		System.out.printf("          �� ǰ�� ����   %15s\n", df.format(k11_itemName.length));
		System.out.printf("       (*)�� ��  �� ǰ   %15s\n", df.format(k11_sumTaxFree));
		//���� ����
		System.out.printf("          �� ��  �� ��   %15s\n", df.format( k11_sumPrice * 10 / 11));
		//�ΰ��� �ø�
		System.out.printf("          ��   ��   ��   %15s\n", df.format(k11_VAT));
		System.out.printf("          ��        ��   %15s\n", df.format(k11_sumPrice + k11_sumTaxFree));
		System.out.printf("�������ݾ�             %15s\n", df.format(k11_sumPrice + k11_sumTaxFree));
		System.out.println("----------------------------------------");
		System.out.println("0012 KEB �ϳ�      541707**0484/35860658");
		System.out.printf("ī�����(IC)    �Ͻú� / %15s\n", df.format(k11_sumPrice + k11_sumTaxFree));
		System.out.println("----------------------------------------");
		System.out.println("           [�ż�������Ʈ ����]");
		System.out.printf("%s ������ ����Ʈ ��Ȳ�Դϴ�.\n", "���¾�");
		System.out.printf("��ȸ�߻�����Ʈ        %10s%8.5s\n", "9350**9995", "164");
		System.out.printf("����(����)����Ʈ  %10s(%10s)\n", df.format(5637), df.format(5473));
		System.out.println("*�ż�������Ʈ ��ȿ�Ⱓ�� 2���Դϴ�.");
		System.out.println("----------------------------------------");
		System.out.println("   ���űݾױ��� ���������ð� �ڵ��ο�");
		System.out.printf("������ȣ :                      %7s\n", "34��****");
		System.out.println("�����ð� :           " + nowTime2);
		System.out.println("----------------------------------------");
		System.out.println("ĳ��:084599 ��OO                    1150");
		System.out.println("  |||||||||||||||||||||||||||||||||||||  ");
		System.out.println("  |||||||||||||||||||||||||||||||||||||  ");
		System.out.println("      " + nowTime3 + "/00119861/00164980/31");
	}
}
