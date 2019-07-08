private void sendStudentDataToServer() {

    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response)
        {

            try
            {
                JSONArray jsonArray = new JSONArray(response);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String code = jsonObject.getString("code");
                String message = jsonObject.getString("message");
                Toast.makeText(AddActivity.this, code+"\n\n"+message, Toast.LENGTH_SHORT).show();
                studentid.setText("");
                name.setText("");
                email.setText("");
                phone.setText("");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

            Toast.makeText(getApplicationContext(), "ERROR IS "+error, Toast.LENGTH_LONG).show();

        }
    }){

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {

            Map<String, String> map = new HashMap<String, String>();
            map.put("student_id",studentid.getText().toString());
            map.put("name",name.getText().toString());
            map.put("email",email.getText().toString());
            map.put("phone",phone.getText().toString());
            return map;
        }
    };

    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
    queue.add(stringRequest);

}


private void SearchStudentFromServer() {

    final String getid = searchbox.getText().toString().trim();

    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            try {
                students.clear();
                JSONArray jsonArray = new JSONArray(response);
                if (jsonArray.length() == 0)
                {
                    Toast.makeText(SearchActivity.this, "No data Found", Toast.LENGTH_SHORT).show();
                }

                for (int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject object = jsonArray.getJSONObject(i);
                    students.add(new Student(
                            object.getString("id"),
                            object.getString("student_id"),
                            object.getString("name"),
                            object.getString("email"),
                            object.getString("phone")
                    ));
                    warning.setVisibility(View.GONE);

                }

                adapter = new StudentAdapter(students,SearchActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(SearchActivity.this, "Error for "+error, Toast.LENGTH_SHORT).show();
        }
    }){

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {

            Map<String, String> map = new HashMap<>();
            map.put("student_id",getid);
            return map;

        }
    };

    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
    queue.add(stringRequest);

}

private void updateservercall() {

    StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiUrl.UPDATE_URL, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            Log.d("Abirresponse",response);

            try
            {
                JSONArray jsonArray = new JSONArray(response);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String code = jsonObject.getString("code");
                String message = jsonObject.getString("message");
                Toast.makeText(StudentDetailsActivity.this, code+"\n\n"+message, Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(StudentDetailsActivity.this, "Error for "+error, Toast.LENGTH_SHORT).show();
        }
    }){

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {

            Map<String, String> map = new HashMap<>();
            map.put("id",id);
            map.put("student_id",std_id.getText().toString());
            map.put("name",std_name.getText().toString());
            map.put("email",std_email.getText().toString());
            map.put("phone",std_phone.getText().toString());
            return map;

        }
    };

    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
    queue.add(stringRequest);



}

private void deleteservercall() {

    StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiUrl.DELETE_URL, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            Log.d("Abirresponse",response);

            try
            {
                JSONArray jsonArray = new JSONArray(response);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                String code = jsonObject.getString("code");
                String message = jsonObject.getString("message");
                Toast.makeText(StudentDetailsActivity.this, code+"\n\n"+message, Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(StudentDetailsActivity.this, "Error for "+error, Toast.LENGTH_SHORT).show();
        }
    }){

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {

            Map<String, String> map = new HashMap<>();
            map.put("id",id);
            return map;

        }
    };

    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
    queue.add(stringRequest);

}

public void ReadDatafROMDatabase()
{

    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.d("serverRsp",response);
            try {

                JSONArray jsonArray = new JSONArray(response);
                if (jsonArray.length() == 0)
                {
                    Toast.makeText(MainActivity.this, "No data Found", Toast.LENGTH_SHORT).show();

                }
                for (int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject object = jsonArray.getJSONObject(i);
                    students.add(new Student(
                            object.getString("id"),
                            object.getString("student_id"),
                            object.getString("name"),
                            object.getString("email"),
                            object.getString("phone")
                    ));


                }

                adapter = new StudentAdapter(students,MainActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

            Toast.makeText(MainActivity.this, "Error for "+error, Toast.LENGTH_SHORT).show();

        }
    });

    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
    queue.add(stringRequest);

}