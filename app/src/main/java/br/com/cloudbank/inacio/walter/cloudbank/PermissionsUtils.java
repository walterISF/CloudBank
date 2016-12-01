package br.com.cloudbank.inacio.walter.cloudbank;

/**
 * Created by aluno on 01/12/16.
 */
/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

    import android.content.pm.PackageManager;
    import android.support.v4.app.ActivityCompat;
    import android.app.Activity;
    import android.support.v4.content.ContextCompat;
    import java.util.ArrayList;
    import java.util.List;

/**
 * Sistemas de permissão do Android 6.0
 * <p/>
 * http://developer.android.com/preview/features/runtime-permissions.html
 */
class PermissionUtils {

    /**
     * Solicita as permissões
     */
    public static boolean validate(Activity activity, int requestCode, String... permissions) {
        List<String> list = new ArrayList<String>();
        for (String permission : permissions) {
            // Valida permissão
            boolean ok = ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
            if (!ok) {
                list.add(permission);
            }
        }
        if (list.isEmpty()) {
            // Tudo ok, retorna true
            return true;
        }

        // Lista de permissões que falta acesso.
        String[] newPermissions = new String[list.size()];
        list.toArray(newPermissions);

        // Solicita permissão
        ActivityCompat.requestPermissions(activity, newPermissions, 1);

        return false;
    }
}