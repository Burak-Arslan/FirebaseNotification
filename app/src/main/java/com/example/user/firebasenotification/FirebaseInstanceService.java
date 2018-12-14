package com.example.user.firebasenotification;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseInstanceService extends FirebaseInstanceIdService {
    String regID;

    @Override
    public void onTokenRefresh() {
        try {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            sendRegistrationToServer(refreshedToken);
        } catch (Exception ex) {
            //LogManager.CreateLog("FirebaseInstanceServiceOld", "onTokenRefresh", ex.getMessage(), "HATA", Enums.LogType.Error);
        }
    }

    public String GetID() {
        String firebaseID = FirebaseInstanceId.getInstance().getToken();
        return firebaseID;
    }

    private void sendRegistrationToServer(String token) {
        regID = token;
    }
}
