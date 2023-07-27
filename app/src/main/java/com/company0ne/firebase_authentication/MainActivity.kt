    package com.company0ne.firebase_authentication

    import android.content.DialogInterface
    import android.content.Intent
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.view.Menu
    import android.view.MenuItem
    import androidx.appcompat.app.AlertDialog
    import com.google.firebase.auth.FirebaseAuth


    class MainActivity : AppCompatActivity() {


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            // Show the Back  up-button in the action bar.
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

        }

        //performing action on Menu items
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu_delete_all, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {

            if (item.itemId == R.id.deleteAll) {
                showDialogMessage()
            } else if (item.itemId == R.id.signOut) {
                //sign out from the Firebase
                FirebaseAuth.getInstance().signOut()
                //and open LoginActivity through intents
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            return super.onOptionsItemSelected(item)
        }
        private fun showDialogMessage() {
            val dialogMessage = AlertDialog.Builder(this)
            dialogMessage.setTitle("Delete All Users")
            dialogMessage.setMessage("If you click Yes, All users will be deleted")
            dialogMessage.setPositiveButton("Yes") { dialog, _ ->
                // Provide functionality on Yes
                dialog.dismiss()
            }
            dialogMessage.setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            dialogMessage.create().show()
        }

        override fun onSupportNavigateUp(): Boolean {
            onBackPressed()
            return true
        }

        override fun onBackPressed() {
            val dialogMessage = AlertDialog.Builder(this)
            dialogMessage.setTitle("Close App")
            dialogMessage.setMessage("Are you sure you want to close the app?")
            dialogMessage.setPositiveButton("Yes") { dialog, _ ->
                moveTaskToBack(true)
                dialog.dismiss()
            }
            dialogMessage.setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            dialogMessage.create().show()
        }
    }














