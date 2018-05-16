package com.example.tf018145.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.litesuits.bluetooth.LiteBleGattCallback;
import com.litesuits.bluetooth.LiteBluetooth;
import com.litesuits.bluetooth.scan.PeriodScanCallback;
import com.litesuits.bluetooth.utils.BluetoothUtil;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class RfidActivity extends AppCompatActivity implements View.OnClickListener {
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private BluetoothSocket btSocket = null;

    private OutputStream outStream = null;

    private InputStream inStream = null;

    private BluetoothChatUtil bluetoothChatUtil = null;

    private int REQUEST_ENABLE_BT = 1;

    private final static String TAG = "ClientActivity";

    private static final UUID SERVICE_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    //    public static final String BLUETOOTH_NAME = "PDT-90P";
    public static final String BLUETOOTH_NAME = "SG-UR-01";
//    public static final String BLUETOOTH_NAME = "猫王·小王子OTR";
//    public static final String BLUETOOTH_NAME = "iPad";
//    public static final String BLUETOOTH_NAME = "TF018145";
    private LiteBluetooth liteBluetooth;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case BluetoothChatUtil.MESSAGE_READ:
                    Toast.makeText(RfidActivity.this, "接收到消息", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("蓝牙", "页面关闭");
        bluetoothChatUtil.disconnect();
        bluetoothChatUtil = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rfid);
        setTitle("RFID");
        Button btn = (Button) findViewById(R.id.button11);
        btn.setOnClickListener(this);

//        获取已配对蓝牙设备
        Set<BluetoothDevice> devices = mBluetoothAdapter.getBondedDevices();
//        initBluetooth();
        for (BluetoothDevice bonddevice : devices) {
            Log.i("蓝牙", "bonded device size =" + bonddevice.getName());
            if (!bonddevice.getName().equals(BLUETOOTH_NAME)) {
                continue;
            }
            bluetoothChatUtil = BluetoothChatUtil.getInstance(this);
            bluetoothChatUtil.registerHandler(mHandler);
            bluetoothChatUtil.connect(bonddevice);
//            try {
//                if(btSocket== null) {
//                    btSocket = bonddevice.createRfcommSocketToServiceRecord(SERVICE_UUID);
//                }
////                btSocket = (BluetoothSocket) bonddevice.getClass().getMethod("createRfcommSocket", new Class[] {int.class}).invoke(bonddevice,1);
//                Log.i("isConnected", btSocket.isConnected() + "");
//                Log.i("getRemoteDevice ", btSocket.getRemoteDevice() + "");
////                if(btSocket.isConnected()){
////                    btSocket.close();
////                }
//                if(btSocket.isConnected()==false) {
//                    btSocket.connect();
//                }
//                Log.i("isConnected", btSocket.isConnected() + "");
//                Log.i("getRemoteDevice ", btSocket.getRemoteDevice() + "");
//                outStream = btSocket.getOutputStream();
//                if (outStream == null) {
//                    Log.i("", "空");
//                }
//                byte[] cmdbytes = new byte[5];
//                byte[] cmd = new byte[]{0x05, 0x00, 0x2F, 0x14, 0x00, 0x00};
//                cmdbytes[0] = (byte) 0xff;
//                cmdbytes[1] = (byte) 0x00;
//                cmdbytes[2] = (byte) 0x03;
//                cmdbytes[3] = (byte) 0x1D;
//                cmdbytes[4] = (byte) 0x0C;
//                outStream.write(cmdbytes);
//                outStream.flush();
//            } catch (Exception e) {
//                Toast.makeText(this, "connect faild", Toast.LENGTH_LONG).show();
//                Log.e(TAG, "create() failed", e);
//                e.printStackTrace();
//            }
//            Log.i("蓝牙", "bonded device name =" + bonddevice.getName() + " address" + bonddevice.getAddress());
        }
    }

    private void initBluetooth() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {//设备不支持蓝牙
            Toast.makeText(getApplicationContext(), "设备不支持蓝牙",
                    Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        //判断蓝牙是否开启
        if (!mBluetoothAdapter.isEnabled()) {//蓝牙未开启
            Intent enableIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
            //mBluetoothAdapter.enable();此方法直接开启蓝牙，不建议这样用。
        }
        //注册广播接收者，监听扫描到的蓝牙设备
        IntentFilter filter = new IntentFilter();
        //发现设备
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mBluetoothReceiver, filter);
    }

    private BroadcastReceiver mBluetoothReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.i(TAG, "mBluetoothReceiver action =" + action);
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //获取蓝牙设备
                BluetoothDevice scanDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (scanDevice == null || scanDevice.getName() == null) return;
                Log.i(TAG, "name=" + scanDevice.getName() + "address=" + scanDevice.getAddress());
                //蓝牙设备名称
                String name = scanDevice.getName();
                if (name != null && name.equals(BLUETOOTH_NAME)) {
                    mBluetoothAdapter.cancelDiscovery(); //取消扫描
//                    mProgressDialog.setTitle(getResources().getString(R.string.progress_connecting));
                    bluetoothChatUtil.connect(scanDevice);
                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
            }
        }
    };

    @Override
    public void onClick(View v) {
        byte[] cmdbytes = new byte[5];
        byte[] cmd = new byte[]{0x05, 0x00, 0x2F, 0x14, 0x00, 0x00};
        cmdbytes[0] = (byte) 0xff;
        cmdbytes[1] = (byte) 0x00;
        cmdbytes[2] = (byte) 0x03;
        cmdbytes[3] = (byte) 0x1D;
        cmdbytes[4] = (byte) 0x0C;
        bluetoothChatUtil.write(cmdbytes);
    }
}
