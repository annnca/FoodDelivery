package com.example.fooddelivery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Build;

import static android.support.v4.content.ContextCompat.getSystemService;

public class NetworkMonitor {
    @SuppressLint("NewApi")
    public void registerConnectivityNetworkMonitor(final Context c) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }

        ConnectivityManager connectivityManager = (ConnectivityManager)c.getSystemService(c.CONNECTIVITY_SERVICE);

        NetworkRequest.Builder builder = new NetworkRequest.Builder();

        connectivityManager.registerNetworkCallback(
                builder.build(),
                new ConnectivityManager.NetworkCallback() {
                    /**
                     * @param network
                     */
                    @Override
                    public void onAvailable(Network network) {

                        c.sendBroadcast(
                                getConnectivityIntent(false)
                        );

                    }

                    /**
                     * @param network
                     */
                    @Override
                    public void onLost(Network network) {

                        c.sendBroadcast(
                                getConnectivityIntent(true)
                        );

                    }
                }

        );

    }

    /**
     * @param noConnection
     * @return
     */
    private Intent getConnectivityIntent(boolean noConnection) {

        Intent intent = new Intent();

        intent.setAction("mypackage.CONNECTIVITY_CHANGE");
        intent.putExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, noConnection);

        return intent;

    }
}
