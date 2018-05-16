package com.example.tf018145.myapplication;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class RfidTest {
    public static void run(OutputStream output, InputStream input) {
        Socket client = null;
        try {
            byte[] cmdbytes = new byte[6];
            cmdbytes[0] = (byte) 0xff;
            cmdbytes[1] = (byte) 0x01;
            cmdbytes[2] = (byte) 0x61;
            cmdbytes[3] = (byte) 0x05;
            cmdbytes[4] = (byte) 0xbd;
            cmdbytes[5] = (byte) 0xb8;
            System.out.println("3.��ʼ���Ͷ�Uidָ��!");
            output.write(cmdbytes);
            output.flush();
            Thread.sleep(2);
            System.out.println("4.��ʼ���ն�Uid�������!");
            byte b[] = new byte[20];
            int len = input.read(b);
            System.out.println("5.��Uid����������ݳ���Ϊ" + len + ",��ʼ���н�������!");
            String uidstr = bytesToHexString(b);
            Log.i("读取成功",uidstr.toString());
            if (len >= 17 && b[5] == 0x00) {
                System.out.println("6.��Uid�ɹ�:" + uidstr.substring(14, 30));
            } else {
                System.out.println("6.��Uidʧ��,���ر���Ϊ" + uidstr);
            }
            input.close();
            output.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
