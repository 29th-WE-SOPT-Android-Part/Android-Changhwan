package changhwan.experiment.sopthomework.ui.view.camera

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import changhwan.experiment.sopthomework.R
import changhwan.experiment.sopthomework.databinding.FragmentCameraBinding
import changhwan.experiment.sopthomework.ui.view.camera.data.CameraData


class CameraFragment : Fragment() {

    private var _binding : FragmentCameraBinding? = null
    private val binding get() = _binding!!
    private lateinit var getContent: ActivityResultLauncher<Intent>
    private lateinit var fContext : Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_camera, container, false)




        binding.lifecycleOwner = viewLifecycleOwner

        initPicUri()
        initIntent()

        return binding.root
    }

    private fun initPicUri(){


        getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val cameraData = CameraData(picUri = MutableLiveData<Uri>().apply { value = it.data?.data })
            binding.camera = cameraData
        }
    }

    private fun initIntent(){
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = MediaStore.Images.Media.CONTENT_TYPE
            type = "image/*"
        }




        binding.cameraButton.setOnClickListener{
            var permission = ContextCompat.checkSelfPermission(fContext, Manifest.permission.READ_EXTERNAL_STORAGE)
            if(permission == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(requireActivity(),arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE)
            } else {
                getContent.launch(intent)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val REQUEST_CODE = 1
    }
}