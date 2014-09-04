package com.qdaily.util;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;
import android.app.Activity;
import android.util.Log;

import com.droison.net.ShareBase;
import com.qdaily.constants.AppConstants;
import com.qdaily.ui.R;

public class ShareTask {

	private Activity mActivity;
	private ShareBase shareBase;
	private final String TAG = "ShareTask";

	public ShareTask(Activity mActivity, ShareBase shareBase) {
		this.mActivity = mActivity;
        this.shareBase = shareBase;
	}

	protected void postData() {
        OnekeyShare oks = new OnekeyShare();
        oks.setNotification(R.drawable.ic_launcher, mActivity.getString(R.string.app_name));
        oks.setTitle(shareBase.getTitle());  //titleUrl是标题的网络链接，仅在人人网和QQ空间使用，否则可以不提供
        oks.setTitleUrl(shareBase.getTitleUrl()); //QQ空间分享标题链接
        oks.setText(shareBase.getContent());//text是分享文本，所有平台都需要这个字段
        oks.setImagePath(shareBase.getImagePath());//imagePath是本地的图片路径，除Linked-In外的所有平台都支持这个字段 */
        oks.setImageUrl(shareBase.getImageurl());// imageUrl是图片的网络路径，新浪微博、人人网、QQ空间和Linked-In支持此字段 */
        oks.setUrl(shareBase.getImageurl()); //url在微信（包括好友、朋友圈收藏）和易信（包括好友和朋友圈）中使用，否则可以不提供 */
        oks.setFilePath(null);//filePath是待分享应用程序的本地路劲，仅在微信（易信）好友和Dropbox中使用
        oks.setComment("赞");//人人网和QQ空间使用的评论
        oks.setSite(mActivity.getString(R.string.app_name));//QQ空间分享网站名称
        oks.setSiteUrl(AppConstants.HTTPURL.server);  //QQ空间分享网站地址
        oks.setSilent(true);
        // 去除注释，可令编辑页面显示为Dialog模式
        oks.setDialogMode();
        // 去除注释，则快捷分享的操作结果将通过OneKeyShareCallback回调
        oks.setCallback(new OneKeyShareCallback());

        oks.show(mActivity);
	}

	public class OneKeyShareCallback implements PlatformActionListener {

		public void onComplete(Platform plat, int action, HashMap<String, Object> res) {
			// Toast.makeText(mActivity, "分享成功", Toast.LENGTH_SHORT).show();
		}

		public void onError(Platform plat, int action, Throwable t) {
			Log.e("shareCallBack", "失败", t);
			// Toast.makeText(mActivity, "分享失败，请稍后重试",
			// Toast.LENGTH_SHORT).show();;
		}

		public void onCancel(Platform plat, int action) {
			// 在这里添加取消分享的处理代码
		}

	}

}
