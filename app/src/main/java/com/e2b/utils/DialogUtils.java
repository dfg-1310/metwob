package com.e2b.utils;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.e2b.R;
import com.e2b.activity.BaseActivity;
import com.e2b.listener.ICustomCallbacks;
import com.e2b.listener.IDialogListener;
import com.e2b.listener.IDialogUploadListener;
import com.e2b.listener.IOptionSelectListener;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class is used for showing validation and error dialog
 */

public class DialogUtils {

    private static final String TAG = DialogUtils.class.getSimpleName();


    public static void showDialog(Context context, String message) {

        final MaterialDialog.Builder materialDialog = new MaterialDialog.Builder(context);
        materialDialog.title(context.getString(R.string.app_name));
        materialDialog.content(message).
                positiveColorRes(R.color.color_CC42B757).
                negativeColorRes(R.color.color_CC42B757)
                .positiveText(context.getString(R.string.ok));

        if (!((Activity) context).isFinishing())
            materialDialog.show();

    }

    public static void showDialogDoubleButton(Context context, String message, String positive, String negative, final IDialogListener iListener) {

        new MaterialDialog.Builder(context).title(context.getString(R.string.app_name)).content(message)
                .positiveColorRes(R.color.color_CC42B757).positiveText(positive)
                .negativeColorRes(R.color.color_CC42B757).negativeText(negative)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        iListener.positiveClick();

                    }
                }).onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(MaterialDialog dialog, DialogAction which) {


            }
        }).show();
    }

    public static void showDialogSingleButton(Context context, String message, String positive, final IDialogListener iListener) {

        new MaterialDialog.Builder(context).title("Why").backgroundColorRes(R.color.white).content(message).contentColorRes(R.color.black)
                .positiveColorRes(R.color.color_4aacc9).positiveText(positive)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        iListener.positiveClick();
                    }
                }).show();
    }
    public static boolean openDialogSignin(BaseActivity activity, String email, String password) {
        if (TextUtils.isEmpty(email)) {
            showDialog(activity, activity.getString(R.string.enter_email));
            return false;
        }
        if (!Validator.isValidEmail(email)) {
            showDialog(activity, activity.getString(R.string.enter_email_validation));
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            showDialog(activity, activity.getString(R.string.please_enter_password));
            return false;
        }
        if (password.length() <= AppConstant.PASSWARD_LENGTH) {
            showDialog(activity, activity.getString(R.string.passwordValidation));
            return false;
        }
        return true;
    }


    public static void showDialog(Context context, String message, String txtPostiveBtn, String txtNegativeBtn, final ICustomCallbacks callback) {
        final MaterialDialog.Builder materialDialog = new MaterialDialog.Builder(context);
        materialDialog.title(context.getString(R.string.app_name));
        materialDialog.content(message).
                positiveColorRes(R.color.color_CC42B757).
                negativeColorRes(R.color.color_CC42B757)
                .positiveText(txtPostiveBtn)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        callback.onOkClicked();
                    }
                })
                .negativeText(txtNegativeBtn)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        callback.onCancelClicked();
                    }
                });

        if (!((Activity) context).isFinishing())
            materialDialog.show();
    }

//    public static void openDialogCameraGallary(final BaseActivity activity, final IDialogUploadListener listner) {
//        final Dialog privacyDialog = new Dialog(activity);
//        privacyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        privacyDialog.setContentView(R.layout.dialog_camera_photo);
//        TextView cameraPhoto = (TextView) privacyDialog.findViewById(R.id.tv_camera);
//        TextView galleryPhoto = (TextView) privacyDialog.findViewById(R.id.tv_gallery);
//        cameraPhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void positiveClick(View v) {
//                listner.positiveClick(true);
//                privacyDialog.dismiss();
//            }
//        });
//        galleryPhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void positiveClick(View v) {
//                listner.positiveClick(false);
//                privacyDialog.dismiss();
//
//            }
//        });
//        privacyDialog.show();
//    }
//
//    public static void openDialogPrivacy(final BaseActivity activity, final IDialogStringDataListener listner) {
//        final Dialog privacyDialog = new Dialog(activity);
//        privacyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        privacyDialog.setContentView(R.layout.dialog_privacy);
//        LinearLayout ll_public = (LinearLayout) privacyDialog.findViewById(R.id.ll_public);
//        LinearLayout ll_friends = (LinearLayout) privacyDialog.findViewById(R.id.ll_friends);
//        LinearLayout ll_only_me = (LinearLayout) privacyDialog.findViewById(R.id.ll_only_me);
//        ll_public.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void positiveClick(View v) {
//                listner.privacyItem(activity.getString(R.string.privacy_public));
//                privacyDialog.dismiss();
//            }
//        });
//
//        ll_friends.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void positiveClick(View v) {
//                listner.privacyItem(activity.getString(R.string.friends));
//                privacyDialog.dismiss();
//
//            }
//        });
//        ll_only_me.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void positiveClick(View v) {
//                listner.privacyItem(activity.getString(R.string.only_me));
//                privacyDialog.dismiss();
//
//            }
//        });
//        privacyDialog.show();
//    }
//
//
//    public static void openDialogVideoUrl(final BaseActivity activity, final IDialogStringDataListener listner) {
//        final Dialog videoUrlDialog = new Dialog(activity);
//        videoUrlDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        videoUrlDialog.setContentView(R.layout.dialog_video_url);
//        TextView tv_done = (TextView) videoUrlDialog.findViewById(R.id.tv_done);
//        TextView tv_cancel = (TextView) videoUrlDialog.findViewById(R.id.tv_cancel);
//        final EditText et_url = (EditText) videoUrlDialog.findViewById(R.id.et_video_link);
//
//        tv_done.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void positiveClick(View v) {
//                listner.privacyItem(et_url.getText().toString());
//                videoUrlDialog.dismiss();
//            }
//        });
//        tv_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void positiveClick(View v) {
//                videoUrlDialog.dismiss();
//
//            }
//        });
//        videoUrlDialog.show();
//    }

public static void showPopUpWithMultipleOptions(Context context, String title, final IOptionSelectListener listener) {
    final String[] lists = getWeeklyMonth();
    final List<String> selectedItems = new ArrayList<>();
    Dialog dialog;
    final ArrayList itemsSelected = new ArrayList();
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setTitle(title);
    builder.setMultiChoiceItems(lists, null, new DialogInterface.OnMultiChoiceClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int selectedItemId,
                                    boolean isSelected) {
                    if (isSelected) {
                        itemsSelected.add(selectedItemId);
                        selectedItems.add(lists[selectedItemId]);
                    } else if (itemsSelected.contains(selectedItemId)) {
                        itemsSelected.remove(Integer.valueOf(selectedItemId));
                        selectedItems.remove(lists[selectedItemId]);
                    }
                }
            })
            .setPositiveButton("Done!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    listener.selectedItems(selectedItems);
                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                }
            });
    dialog = builder.create();
    dialog.show();
}

    public static String[] getWeeklyMonth(){
        String[] week = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday","Friday","Saturday"};
        return week;
    }

    public static void showDialogCameraGallary(String title, final Context activity, final IDialogUploadListener listner) {
        final Dialog privacyDialog = new Dialog(activity);
        privacyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        privacyDialog.setContentView(R.layout.dialog_camera_photo);
        TextView header = (TextView) privacyDialog.findViewById(R.id.tv_header);
        header.setText(title);
        TextView cameraPhoto = (TextView) privacyDialog.findViewById(R.id.tv_camera);
        TextView galleryPhoto = (TextView) privacyDialog.findViewById(R.id.tv_gallery);
        cameraPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onClick(true);
                privacyDialog.dismiss();
            }
        });
        galleryPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onClick(false);
                privacyDialog.dismiss();

            }
        });
        privacyDialog.show();
    }
}


