package valentinbourgeois.miniprojet.view;

import android.content.Intent;
import android.util.Log;
import android.view.View;

public class CustomOnClickListener implements View.OnClickListener{
    private String _key;
    private String _value;
    private Class _target;

    public CustomOnClickListener(String key, String value, Class target) {
        _key = key;
        _value = value;
        _target = target;
    }

    @Override
    public void onClick(View v) {
        Log.d("ITENT",">>>>>>>>>>>>>>>>>>>>");
        Intent intent = new Intent(v.getContext(), _target);
        intent.putExtra(_key,_value);
        v.getContext().startActivity(intent);
    }
}
