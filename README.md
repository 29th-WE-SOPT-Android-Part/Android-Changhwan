# Android-Changhwan
![github_이창환_ver1-24](https://user-images.githubusercontent.com/70698151/135754493-6025ae3d-c4d2-4181-8359-1d921f91d59e.png)

<details markdown="1">
<summary>1주차</summary>

# -실행화면

https://user-images.githubusercontent.com/54737136/136679833-82fab293-e492-499e-a809-3e36ce005694.mp4

# -코드설명

## signin
```
binding.loginButton.setOnClickListener {
            if (binding.inEditId.text.toString() != "" && binding.inEditPw.text.toString() != "") {
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "이창환님 환영합니다", Toast.LENGTH_SHORT).show()
            } else {
                binding.inEditId.text.clear()
                binding.inEditPw.text.clear()
                Toast.makeText(this, "로그인실패", Toast.LENGTH_SHORT).show()
            }
        }
```
edit text에 내용이 있나 확인후 있다면 토스트메세지를 띄우며 인텐트 하는부분

```
 getResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK) {
                binding.inEditId.text.clear()
                binding.inEditId.text.append(it.data?.getStringExtra("Id"))
                binding.inEditPw.text.clear()
                binding.inEditPw.text.append(it.data?.getStringExtra("Pw"))
            }
        }
```
signup으로 넘어갔다 돌아왔을때 데이터 받아와서 처리하는 파트


## signup

```
binding.signUpDone.setOnClickListener{
            if(binding.upEditId.text.toString() != "" && binding.upEditPw.text.toString() != "" && binding.upEditName.text.toString() != ""){
                val intent = Intent(this,SignInActivity::class.java).apply {
                    this.putExtra("Id",binding.upEditId.text.toString())
                    this.putExtra("Pw",binding.upEditPw.text.toString())
                }
                setResult(RESULT_OK,intent)
                finish()
            }else{
                Toast.makeText(this,"입력되지않은 정보가 있습니다",Toast.LENGTH_SHORT).show()
            }
        }
```
finish로 돌아가면서 가져가야하는 데이터들 putExtra로 가져가는부분

## home

```
binding.homeToGit.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/2chang5"))
            startActivity(intent)
        }
```
암시적 인텐트로 웹으로 넘어가는부분


# -이번 과제를 통해 배운내용

## level1

### 1. Editable
edittext 를 코드단에서 텍스트를 넣어주기위해 

![image](https://user-images.githubusercontent.com/54737136/136599655-e311e8e0-332c-4ca8-9805-53f3b74765b0.png)

이런식으로 텍스트에 직접 문자열을 넣어줬는데 자료형이 안맞아서 적용할수가 없었다.
edittext의 텍스트는 Editable TYPE이였는데
Editable라는 인터페이스를 구현한 객체이므로 Editable 안에 정의된 clear() append()같은 메서드를 사용해서 조작할수있었다.

![image](https://user-images.githubusercontent.com/54737136/136599778-400c4046-3e45-4d60-bcce-bf2c94410cfa.png)


### 2.finish
기존에 맨날 페이지를 이동할때 startActivity를 통해서만 움직였고 뭔가 이상했다.
스택에 쌓여있는 거쳐왔던 화면으로 돌아갈때는 back 버튼을 물리적으로 눌러서 돌아갔는데 
이렇게 기존 스택에 최상단에있는 화면에서 스택에 쌓여있는 화면으로 돌아가기 위해
백버튼과 같은 효과를 가진 finish() 를 통해서 화면을 종료하고 기존에 화면으로 돌아갈수있다.

![image](https://user-images.githubusercontent.com/54737136/136600033-4af66c31-c17e-4c24-a233-cb83914e76f2.png)

사용예시


### 3.imageView
image view를 사용할때 기존에는
srcCompat 속성에 소스를 설정했는데 이번에 이상하게 이미지가 로드되지 않았다
구글링해본결과

srcCompat은 Android Support Library에 포함된 방식(method of work)이다. (AppCompat에 있음)
안드로이드 서포트 라이브러리를 간단하게 의미하면 '어느 버전에서나 똑같이 구현할 수 있는'을 행하는 라이브러리라고 정의할 수 있다.
srcCompat은 vector Drawables(즉, 그림)를 모든 안드로이드에서 표현하게 해주는 안드 서포트 라이브러리 안에 구현된 기능이다. 그러므로 내가 해당 라이브러리를 쓰고 있는 것이 아니라면 당연히 srcCompat으로 리소스를 지정해봤자 에뮬레이터가 제대로 그려줄 리 없다.
그러므로 srcCompat을 사용하여 이미지를 그리고 싶다면 ImageView태그가 아닌 android.support.v7.widget.AppCompatImageView를 사용해야 한다.
또한 네임스페이스도 지정해줘야 한다 (xmlns:app="http://schemas.android.com/apk/res-auto")

minSDKversion이 롤리팝(5.0, level 21)이상이라면 src방식을 사용할 수 있는데 이때부터 안드로이드에서 머테리얼 디자인이 생겼기 때문이다.
src의 경우 xml에서 ImageView태그일 경우에 사용할 수 있으며 이때부터 src 설정을 사용하여 이미지를 넣을 수 있다.
srcCompat과 달리 롤리팝 이상부터 기본으로 지원하며 롤리팝 이전 버전을 지원해야 할 경우에는 반드시 srcCompat을 사용해야 한다.

과거에는 모든 버전을 커버할 수 있다는 점 때문에 srcCompat이 나름 가치가 있었으나 현재는 대부분의 사람들이 최소 롤리팝 이상의 버전을 쓰기 때문에 장점이 상당히 희석되었다. 

출처: https://ammff.tistory.com/100 [아메리카노가 그렇게 맛있답니다 여러분]

이렇다고한다.

그래서 src 속성으로 바꾸니 해결되었다.

## level2
### 1.registerForActivityResult

(추후에 한번 살펴볼것: parcelable로 객체 전달해보자)

원래는 기존에 startActivityForResult() 와 onActivityResult()을 사용했었는데 deprecated 되고 그 대용으로
registerForActivityResult가 들어왔다. 

용도를 살펴보자면

startActivity : 새 액티비티를 열어줌 (단방향)

registerForActivityResult : 새 액티비티를 열어줌 + 결과값 전달 (쌍방향)

간단히 말해서 액티비티를 열되 갈때는 하던대로 putExtra() 를 이용해서 데이터 전달하고
열린 액티비티가 finish로 종료되었을때 필요한 정보를 가지고 기존 액티비티로 돌아올수있게 하는것이다.
그리고 종료시점에 코드를 실행해주어 가지고온 데이터를 처리할수있다.

기존 startActivityForResult() 와 비교하여 장점은 이러하다.

-디커플링 및 관심사 분리 : 기존 액티비티 또는 프레그먼트의 onActivityResult에서 if와 else if로 도배되던 비즈니스 로직들이 콜백메서드 또는 분리된 클래스 단위로 쪼개어져서 관리될 수 있다. 이는 코드의 가독성을 높이고, 유닛테스트를 수월하게 하며, 유지보수측면에서도 많은 도움이 된다.

-Type-Safety : ActivityResultContract는 입력 데이터와 출력 데이터의 타입을 강제하기 때문에 잘못된 타입으로 캐스팅하는 사소한 실수를 미연에 방지시켜준다.

-NPE 방지 : Intent로 부터 데이터를 얻으려고 할 때 NullPointerException이 발생하는 경험을 누구나 한번쯤은 해보았을 것이다. 새로운 API는 NPE가 발생할 확률을 줄여줄 것이다

출처 : https://charlezz.medium.com/%EC%95%A1%ED%8B%B0%EB%B9%84%ED%8B%B0-%EA%B2%B0%EA%B3%BC-%EC%B2%98%EB%A6%AC%ED%95%98%EA%B8%B0-good-bye-startactivityforresult-onactivityresult-82bafc50edac

위의 링크에 내부적으로 어떻게 돌아가는지에대해 자세히 나와있다.
contract를 직접 정의하고 등록하여 사용할수도있지만
이미 정의된 contract를 사용할수도있다.

![image](https://user-images.githubusercontent.com/54737136/136600592-ba246845-b2be-4d91-b29f-7587e702b22c.png)

이렇게 정의된 contract를 적절한 시기에 사용하면된다.

예를들어 이미지 등의 컨텐츠의 uri를 받아오고싶다면 getContent를 사용하게되는식이다.



이제 간단하게 사용법을 보자면

a엑티비티에서 b 엑티비티로 넘어갔다가 정보를 들고 a로 다시 넘어오는 상황이다
우선 a액티비티에서는 registerForActivityResult함수를 이용해서 callback을 등록해준다.
result를 받기위해 StartActivityForResult를 이용한다.

![image](https://user-images.githubusercontent.com/54737136/136600715-aaca32b5-03e8-4159-9a6e-5cc0682517e8.png)

람다식 안에서
그래서 result로 넘어오는 결과를 resultCode를 확인하고 data를 받아서 데이터 처리를 해주면된다.
data에는 intent에 putExtra로 넣어놓은것들을 뽑아서쓸수있다.


그리고 이제 lacuch를 시켜줘야하는데 intent에 넘어가려는것들 요소 넣어주고
인자에 Intent를 넘겨주며 launch를 실행시킨다.

![image](https://user-images.githubusercontent.com/54737136/136600778-b0460655-b2c2-4329-910e-327bfc550dfe.png)

다음 엑티비티b에서 해야할일을보자

엑티비티 b에서는 intent에 원하는 정보 putExtra로 넣고 setResult함수에 인자로 resultcode와 intent를 넣어주고
finish()로 a로 넘어가면된다.

![image](https://user-images.githubusercontent.com/54737136/136600833-ea0119ff-6435-42a4-81b9-12047e6cfc59.png)


## 2.명시적,암시적 인텐트
인텐트는 한마디로 화면이 옮겨지거나 전화를 걸거나 웹페이지들을 열거나 할때 정보를 4대 컴포넌트끼리 유기적으로 정보전달을하며 작동할수있게 해주는 요소이다.

또한 인텐트는 자신이 만든 앱안에서 활동하는 것 뿐만 아니라 내가 만들지 않은 타 애플리케이션의 기능을 수행할 수 있다.
즉 안드로이드 시스템은 내가 만든 인텐트의 정보를 처리하면서 내가 만든 액티비키나 애플리케이션의 구성요소가 해야할 일을 지정하는 것 이외에도 타 애플리케이션의 기능을 수행하는 등 훨씬 유연한 기능의 애플리케이션을 만들 수 있게 한다.

이제 명시적 인텐트와 암시적 인텐트의 차이점을 봐보자

명시적이벤트: 인텐트에 클래스 객체나 컴포넌트 이름을 지정하여 호출할대상을 확실히 알수있는경우에 사용,

주로 어플리케이션 내부에서 사용한다.

-> 특정 컴포넌트나 액티비티가 명확하게 실행되어야할경우 사용한다. 즉 화면이동 등 앱내에서 주로 사용된다.

암시적인텐트:
인텐트의 액션과 데이터가 정해졌지만 호출할 대상이 달라질수있는경우 암시적 인텐트를 사용
예를들어 웹을 여는경우에 직접 구현하지 않고 안드로이드 시스템내에 있는 웹브라우져를 끌어다 쓰는데 
그때 브라우져는 여러개가 깔려있을수 있기에 정확히 어느걸 호출할것인지 모르는 상황이다. 이런경우 암시적 인텐트를 통해 정보처리를 할수있는 적절한 컴포넌트를 찾아와 사용자에게 고르게하고 그에의해 처리한 결과를 보여주는것이다.

한마디로 정리해서 일과 데이터는 정해졌는데 외부에서 처리하려할때 그 일을 누가할지는 정해져있지않아 그냥 떠넘겨 버리고 그일을 할 프로그램은 안드로이드 내부적으로 정해지거나 여러개일경우 사용자가 정하도록 하는것이다.

## 3.ConstraintDimensionRatio
비율을 정해서 사용하고싶을때 weidth나 height 둘중하나만 정하고 하나는 0dp로 설정해주고
가로 세로의 비율을 ConstraintDimensionRatio 속성을 통해서 정해준다

비율 표현방법은 이러하다

- app:layout_constraintDimensionRatio="1:1" (width:height로 표현하는 방법)

- app:layout_constraintDimensionRatio="1.0" (width와 height의 비율을 float값으로 표현하는 방법)

</details>

<details markdown="1">
<summary>2주차</summary>

# - 실행화면

https://user-images.githubusercontent.com/54737136/138426985-5a53bdb3-e970-45cc-be52-f525bf2128db.mp4





# - 코드설명

일단 follower는 리스트형태 repository는 그리드 형태로해서 level2,3 적용은 다 follower에다가만 했다.



level1은 당연히 수행했고



### level2




level 2-1은

들어갈때 상세화면을 구성하는것은 DetailFragment를 만들어 거기에 데이터를 arguments로 전달해서 구성했고

밑에 설명은 이름이 뭔가에 따라서 그냥 databinding으로 지가알아서 바뀌게 만들었다.

보기좋으라고 필요한부분만 코드를 잘라서 넣어놨는데 잘한건지는 모르겠다 피드백 부탁드립니다.




**2-1**

DetailActivity.kt

```

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var name: String
    private var src by Delegates.notNull<Int>()
    lateinit var detailIntroduce : MutableLiveData<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.detail = this
        binding.lifecycleOwner = this

        name = intent.getStringExtra("name")!!
        src = intent.getIntExtra("src",R.drawable.pig)

        siteFragment()
        decideDetailIntroduction()

    }

    fun siteFragment(){
        val detailFragment = DetailFragment()
        var bundle = Bundle()
        bundle.putString("name",name)
        bundle.putInt("src",src)
        detailFragment.arguments = bundle

        supportFragmentManager.beginTransaction().add(R.id.detailFragmentFrame,detailFragment).commit()
    }

    fun decideDetailIntroduction(){
        if (name == "문다빈"){
            detailIntroduce=MutableLiveData<String>().apply { value = "고향은 경상남도 합천이고,현재 24살이며 안드로이드 파트장을 맡고있음.. 안드 좋아. 안드 좋아. 안드 좋아.안드 좋아. 안드 좋아. 안드 좋아. 안드 좋... "}
        }else if (name == "장혜령"){
            detailIntroduce=MutableLiveData<String>().apply { value = "누군지 몰라요 최송합니다"}
        }
   // 중략

    }

}
```



DetailFragment.kt

```
package changhwan.experiment.sopthomework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import changhwan.experiment.sopthomework.databinding.FragmentDetailBinding
import kotlin.properties.Delegates


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var name : String
    private var src by Delegates.notNull<Int>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        name = arguments?.getString("name")!!
        src = arguments?.getInt("src",R.drawable.pig)!!
        binding.detailImage.setImageResource(src!!)
        binding.detailName.text = name
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
```





**2-2**

과제중 알게된것에 자세히 설명해놨다

CustomDividerDecoration.kt

```
package changhwan.experiment.sopthomework

import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView

class CustomDividerDecoration(private val height: Float,private val padding: Float, @ColorInt private val color: Int,private val margin : Int):RecyclerView.ItemDecoration() {

    private val paint = Paint()

    init{
        paint.color = color
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingStart + padding
        val right = parent.width - parent.paddingEnd - padding

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = ( child.bottom.toFloat() + margin)
            val bottom = child.bottom.toFloat() + height + margin

            c.drawRect(left, top, right, bottom, paint)

        }
    }
}
```



CustomMarginDecoration.kt

```
package changhwan.experiment.sopthomework

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomMarginDecoration(private val padding: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = padding
        outRect.bottom = padding
        outRect.left = padding
        outRect.right = padding
    }
}
```



FollowerFragment.kt

```
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        siteFollowerRecycler()

        binding.followerRecycle.addItemDecoration(CustomMarginDecoration(50))
        binding.followerRecycle.addItemDecoration(CustomDividerDecoration(10f,10f, resources.getColor(R.color.main),40))
```



**2-3**

이것도 과제중 알게된것에 자세히 설명해놨다



itemActionListener.kt

```
package changhwan.experiment.sopthomework

interface ItemActionListener {
    fun onItemMoved(from: Int, to: Int)
    fun onItemSwiped(position: Int)
}
```



ItemDragListener.kt

```
package changhwan.experiment.sopthomework

interface ItemActionListener {
    fun onItemMoved(from: Int, to: Int)
    fun onItemSwiped(position: Int)
}
```



FollowerAdapter.kt

```
class FollowerAdapter(private val listener: ItemDragListener) :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(), ItemActionListener {
    //...
    
     override fun onItemMoved(from: Int, to: Int) {
        if (from == to) {
            return
        }

        val fromItem = followerData.removeAt(from)
        followerData.add(to, fromItem)
        notifyItemMoved(from, to)
    }

    override fun onItemSwiped(position: Int) {
        followerData.removeAt(position)
        notifyItemRemoved(position)
    }
    
    
    inner class FollowerViewHolder(
        private val binding: FollowerItemBinding,
        listener: ItemDragListener
    ) : RecyclerView.ViewHolder(binding.root) {
       
        init {
           
            binding.root.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    listener.onStartDrag(this)
                }
                false
            }
        }

    }
}
```



ItemTouchHelperCallBack.kt



```
package changhwan.experiment.sopthomework

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(val listener: ItemActionListener) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.DOWN or ItemTouchHelper.UP
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags,swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        listener.onItemMoved(viewHolder!!.adapterPosition, target!!.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.onItemSwiped(viewHolder!!.adapterPosition)
    }

    override fun isLongPressDragEnabled(): Boolean = true
}
```



FollowerFragment.kt

```
class FollowerFragment : Fragment(), ItemDragListener {
	
    private lateinit var itemTouchHelper : ItemTouchHelper
    
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       
        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(followerAdapter))
        itemTouchHelper.attachToRecyclerView(binding.followerRecycle)
    }
    
     override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        //이부분 참고한 블로그와 다르게 아무것도 없어야지만 돌아간다 이해안됨 이부분은 추가적인 공부해야겠다
    }
}
```







### level3

**3-1**

은 정보만 과제중 알게된것에 작성하고 적용하지는 않았다.



**3-2**

DiffUtil은 oldList와 newList를 비교하여 차이를 계산하고, newList로 갱신해주는 유틸리티 클래스이다.

즉, 이 클래스를 사용하면 아이템 변경의 구체적인 상황에 따라 Adapter의 적절한 메소드를 호출하지 않아도 된다.



ContactDiffUtill.kt

```
package changhwan.experiment.sopthomework

import androidx.recyclerview.widget.DiffUtil

class ContactDiffUtil(private val oldList: List<FollowerData>, private val currentList: List<FollowerData>):
    DiffUtil.Callback(){
    override fun getOldListSize(): Int =oldList.size

    override fun getNewListSize(): Int =currentList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].followerName==currentList[newItemPosition].followerName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]==currentList[newItemPosition]
    }

}
```

총 4개 메소드를 오버라이드 해줘야 한다. 메소드는 이름명과 리턴값을 보면 어떤 역할을 하는지 쉽게 예측할 수 있다.



FollowerAdapter.kt

```
//diffUtill 부분 이상하면 나중에 바꿔야함
fun setContact(contacts: List<FollowerData>){
    val diffResult= DiffUtil.calculateDiff(ContactDiffUtil(this.followerData, followerData), false)
    diffResult.dispatchUpdatesTo(this)
    this.followerData=followerData
}
//여기까지 diffUtill
```

코드의 뜻은,

1. calculateDiff()로 oldList와 newList의 차이를 계산한다.
2. 차이 값을 업데이트하고, (notify~ 기능와 같다고 보면 된다).
3. list가 갱신되었으므로 기존 this.contacts를 newList인 contacts로 업데이트한다.





FollowerFragment.kt

```
//diffUtill부분 원래는 followerAdapter.notifyDataSetChanged()였음
followerAdapter.setContact(followerAdapter.followerData)
//여기까지
```

# -이번 과제를 통해 배운내용



## level2

### 2-1



#### 1.리사이클러뷰 항목마다 이벤트 리스너 달아주기



각 항목마다 클릭 이벤트 리스너 달아서 이벤트를 처리해보자

ViewHolder 혹은 onBindViewHolder() 함수 두곳에서 이벤트 처리 하는 방법이 있는데



우선 viewholder에서 처리해주는것부터 부터 봐볼것이다.



**1-1ViewHolder에서 처리**

우리가 viewbinding 을 이용해서 viewHolder를 만들었기에 ViewHolder의 생성자로 binding객체를 꽂아줬다.

그래서 이 binding객체의 root가 리사이클러뷰가 표현하는 항목하나 즉 item의 레이아웃에 접근할수있다

그래서 init함수를 만들어 root에 onClicklistener를 추가한다.

![img](https://blog.kakaocdn.net/dn/91zgE/btripFeEZ0k/Da5dKh4z0OaHsgxkGNwX0K/img.png)





**1-2 onBindViewHolder() 에서 처리**



이 함수 안에서도 item에 대한 클릭 리스너를 정의할수있다. 하지만 난 앞전의 방법을 사용했다

![img](https://blog.kakaocdn.net/dn/LmcbP/btriwwzZJHf/VYiQ0o2zrkKTtgiKwEKalk/img.png)

결과적으로 Holder 클래스 내부에서 사용하는것과 같다.





### 2.리사이클러 뷰 어댑터에서 startActivity해보기



리사이클러 뷰에서 아이템을 클릭해서 이벤트를 발생까지는 시켰는데 원하는 이벤트가 새로운 액티비티 실행일 경우

어댑터에서 이벤트를 실행시켜주고있기에 기존에 액티비티에서 Intent에 넣어줬던 인자들을 그대로 넣어주면 안된다.



일단 clickListener안에 intent추가하고 첫번째 인자로 binding.root에 context가 있기에 그걸 사용한다.

그래서 컨텍스트를 맞춰서 넣어주고 두번째 인자로 이동하려는 액티비티 넣고



startActivity함수에 첫번째 인자로 binding.root의 context, 두번째 인자로 intent, 세번째로 별다른 옵션이없다면 null을 입력하면된다.





![img](https://blog.kakaocdn.net/dn/kFvli/btrioFZW6lG/yJeInBOnV5kWekkRW6Mhc0/img.png)





### 3. activity에서 activity로 데이터 옮기기



**데이터 보내기**

첫번째 방법

```
// 제일 단순하고 쉬운 방법
val intent = Intent(this,옮겨갈 액티비티::class.java)
intent.putExtra("num1",1) //데이터 넣기
intent.putExtra("num2",2) //데이터 넣기
startActivity(intent)
```

두번째 방법

```
val intent = Intent(this@Intent1,Intent2::class.java).apply {
	this.putExtra("num1",1) // 데이터 넣기
   	this.putExtra("num2",2) // 데이터 넣기
}
startActivity(intent)
//코틀린의 유용한 기능!🤩 apply
//한눈에 모아서 볼 수 있어서 유용한 듯
```



**데이터 받기**

```
val number1 = intent.getIntExtra("num1", 0)
val number2 = intent.getIntExtra("num2", 0)
```





### 4. Activity에서 Fragment로 데이터 주고 받기

**데이터 보내기**

```
var fragment2 = Fragment2()
var bundle = Bundle()
bundle.putInt("num1",1)
bundle.putInt("num2",2)
fragment2.arguments = bundle //fragment의 arguments에 데이터를 담은 bundle을 넘겨줌

activity?.supportFragmentManager!!.beginTransaction()
                        .replace(R.id.view_main, fragment2)
                        .commit()
```



**데이터 받기**

```
val num1 = arguments?.getInt("num1")
val num2 = arguments?.getInt("num2")
```





### **2-2**



itemDecoration 활용해서 구분선과 간격주기



xml파일에서 margin이나 구분선을 어느정도 만들수있지만 이거는 정확히말하자면 상하단 끝쪽에 margin은 한번만 들어가고 나머지 중간부분은 두번씩 들어가는문제

구분선은 xml내에서 view를 추가해서 넣으면 레이아웃에 불필요한 뷰를 추가함으로써 레이아웃 계층이 증가하고 그에따라 성능에 안좋은 영향을 미치며

좌우로 스와이프 하는 애니메이션이있다면 구분선이 같이 움직인다.



그래서 itemDecoration을 사용하는데 itemDecoration 클래스는 Recyclerview 내부에 있는 추상 클래스이다.

이름처럼 RectclerView의 아이템들을 꾸미는 역할을 한다.

사실 커스텀 하는대로 많은 기능들을 구현할 수 있으므로 하고싶은게 있으면 구글링해서 사용해야겠다 근데 왜 죄다 예제코드가 자바냐고!!!!!!!!!!!!!

대표적으로 구분선이나 여백을 넣는데 많이들 사용한다.





내부 함수가 3가지가 있는데

1.onDraw

아이템이 그려지기 전에 호출됨으로 아이템(viewholder)보다 아래에 위치하게된다 아이템과 onDraw가 그리는 것이 겹친다면 아이템이 덮어씌워서 onDraw가 그리는 것이 안보인다.



2.onDrawOver

아이템이 그려지고 난다음에 호출되는 함수로 이거는 겹친다면 아이템을 가릴수있다.



3.getItemOffsets

각 항목을 배치할때 호출한다 -> margin을 줄때 사용

outRect에 원하는 크기에 간격을 (왼쪽, 위쪽, 오른쪽, 아래쪽) 의 4개 필드에 설정해준다.





그래서 일단 만들어보자.



구글링하면 예제 겁나게 많다 복잡한거는 더더욱많다. 그리고 뭔소리인지 이해하기 좀 난해하다

그냥 쉬운 것들로 적용해보며 필요할때 마다 만들어서 사용해 나가고 난이도를 조금씩 높여야겠다.



**가장쉬운 margin만들기**

```
package changhwan.experiment.sopthomework

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomMarginDecoration(private val padding: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = padding
        outRect.bottom = padding
        outRect.left = padding
        outRect.right = padding
    }
}
```

따로 class파일을 하나파서 itemDecoration을 상속받은후

생성자로 띄울 값을 받아서

getItemOffsets를 오버라이딩 할때 사방 top,bottom,left,right에 생성자로 받은 값을 넣어서 margin을 확보했다.



적용은 적용시킬 리사이클러뷰에다

```
 binding.followerRecycle.addItemDecoration(CustomMarginDecoration(50))
```

이런식으로 addItemDecoration으로 넣어주면된다.(생명주기상 화면이 그려지고 난후에 실행시켜야하는것 같다)





**가장쉬운 구분선 만들기**

```
package changhwan.experiment.sopthomework

import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView

class CustomDividerDecoration(private val height: Float,private val padding: Float, @ColorInt private val color: Int,private val margin : Int):RecyclerView.ItemDecoration() {

    private val paint = Paint()

    init{
        paint.color = color
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingStart + padding
        val right = parent.width - parent.paddingEnd - padding

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = ( child.bottom.toFloat() + margin)
            val bottom = child.bottom.toFloat() + height + margin

            c.drawRect(left, top, right, bottom, paint)

        }
    }
}
```



구분선이 좀빡세다



for문에 들어가는것들이 뭔소리인가 싶은데 좀 공통적으로 예제마다 거의 겹친다.



몇가지 예제들의 부분부분을 따서 섞어서 사용했다



생성자에 들어가는 height에 따라 구분선의 굵기가 달라지고

padding에 따라 좌우에 여백이생기며

color은 색깔 설정이고

margin은 얼마나 띄울지 거리설정이다.

근데 이런것도 내가 뭐넣을지 알아서 결정이다 어짜피.





### **2-3**

recyclerview의 drag&drop swipe to Dismiss 구현



[ItemTouchHelper](https://developer.android.com/reference/android/support/v7/widget/helper/ItemTouchHelper)는 RecyclerView.ItemDecoration의 서브 클래스이다. RecyclerView 및 Callback 클래스와 함께 작동하며, 사용자가 이러한 액션을 수행할 때 이벤트를 수신한다. 우리는 지원하는 기능에 따라 메서드를 재정의해서 사용하면 된다.



[ItemTouchHelper.Callback](https://developer.android.com/reference/android/support/v7/widget/helper/ItemTouchHelper.Callback)은 추상 클래스로 추상 메서드인 getMovementFlags(), onMove(), onSwiped()를 필수로 재정의해야 한다. 아니면 Wrapper 클래스인 [ItemTouchHelper.SimpleCallback](https://developer.android.com/reference/android/support/v7/widget/helper/ItemTouchHelper.SimpleCallback)을 이용해도 된다.



이제 순서대로 구현하는것을 쫒아가보자



**1.ItemDragListener.kt 만들기**

```
interface ItemDragListener {
    fun onStartDrag(viewHolder :RecyclerView.ViewHolder)
}
```

사용자가 Drag 액션을 시작할 때 itemTouchHelper에 이벤트를 전달한다.





**2.ItemActionListener.kt 만들기**

```
interface ItemActionListener {
    fun onItemMoved(from: Int, to: Int)
    fun onItemSwiped(position: Int)
}
```

아이템이 Drag & Drop 됐거나 Swiped 됐을 때 어댑터에 이벤트를 전달한다.





**3.adapter에서  ItemActionListener 인터페이스를 구현**

```
class FollowerAdapter(private val listener: ItemDragListener) :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(), ItemActionListener {
    //...
    
     override fun onItemMoved(from: Int, to: Int) {
        if (from == to) {
            return
        }

        val fromItem = followerData.removeAt(from)
        followerData.add(to, fromItem)
        notifyItemMoved(from, to)
    }

    override fun onItemSwiped(position: Int) {
        followerData.removeAt(position)
        notifyItemRemoved(position)
    }
}
```



어댑터에서는 ItemActionListener 인터페이스를 구현한다. onItemMoved(), onItemSwiped()을 재정의하여 아이템 이동과 제거 코드를 작성한다. 이때 어댑터가 아이템 변경 사항을 인식할 수 있도록 notifyItemMoved(), notifyItemRemoved()를 호출해야 한다.



**4.viewAdapter에서 OnTouchListener달아주기**

```
inner class FollowerViewHolder(
        private val binding: FollowerItemBinding,
        listener: ItemDragListener
    ) : RecyclerView.ViewHolder(binding.root) {
       
        init {
           
            binding.root.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    listener.onStartDrag(this)
                }
                false
            }
        }

    }
```



어댑터 생성자의 파라미터로 받은 ItemDragListener는 뷰홀더에서 사용된다. 여기서는 드래그 핸들을 통한 아이템 이동을 구현하고자 하기 때문에, 드래그 핸들 뷰에 터치 리스너를 달아준다. 그리고 사용자가 [ACTION_DOWN](https://developer.android.com/reference/android/view/MotionEvent.html#ACTION_DOWN) 액션을 취했을 때 listener.onStartDrag()를 호출한다.





**5.ItemTouchHelperCallback.kt 작성**

```
package changhwan.experiment.sopthomework

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(val listener: ItemActionListener) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.DOWN or ItemTouchHelper.UP
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags,swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        listener.onItemMoved(viewHolder!!.adapterPosition, target!!.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.onItemSwiped(viewHolder!!.adapterPosition)
    }

    override fun isLongPressDragEnabled(): Boolean = true
}
```



[ItemTouchHelper.Callback](https://developer.android.com/reference/android/support/v7/widget/helper/ItemTouchHelper.Callback)을 상속받는 ItemTouchHelperCallback 클래스를 구현한다. 생성자의 파라미터로 ItemActionListener를 받는다.



5-1 우선 getMovementFlags()를 재정의해 Drag 및 Swipe 이벤트의 방향을 지정한다.



5-2 아이템이 Drag 되면 [ItemTouchHelper](https://developer.android.com/reference/android/support/v7/widget/helper/ItemTouchHelper)는 onMove()를 호출한다. 이때 ItemActionListener로 어댑터에

fromPosition과 toPosition을 파라미터와 함께 콜백을 전달한다.



5-3 아이템이 Swipe 되면 [ItemTouchHelper](https://developer.android.com/reference/android/support/v7/widget/helper/ItemTouchHelper)는 범위를 벗어날 때까지 애니메이션을 적용한 후 onSwiped()를 호출한다.

이때 ItemActionListener로 어댑터에 제거할 아이템의 position을 파라미터와 함께 콜백을 전달한다.



5-4 isLongPressDragEnabled()은 아이템을 길게 누르면 Drag & Drop 작업을 시작해야 하는지를 반환한다. 디폴트는

true이다





**6.사용처(activity 혹은 fragment) 에서 액티비티에서는 ItemDragListener 인터페이스를 구현**

```
class FollowerFragment : Fragment(), ItemDragListener {
	
    private lateinit var itemTouchHelper : ItemTouchHelper
    
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       
        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(followerAdapter))
        itemTouchHelper.attachToRecyclerView(binding.followerRecycle)
    }
    
     override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        //이부분 참고한 블로그와 다르게 아무것도 없어야지만 돌아간다 이해안됨 이부분은 추가적인 공부해야겠다
    }
}
```



이렇게하면 원했던 기능들을 구현할수있다.







## **Level3**



### **3-1**



보일러 플레이트 코드 어떻게 잡을것인가?

딱히 당장 많이 개선할부분이 없는데 복잡해서 방법만 알아두고 가야겠다.



어노테이션 프로세서 같은걸 사용해서 자동화 작업을 하는것이 좋다지만 너무 복잡하다 당장 이거하다 머리터진다.



그래서 복잡하지 않은 방법을 봤다

base코드 즉 BaseActivity, BaseFragment같은 코드들을 만들어놓고 상속해서 사용하는것이다.



장단점을 살펴보면



**장점**



-데이터 바인딩뿐만 아니라 Activity들이 공통적으로 수행해야 하는 코드가 있다면 BaseActivity를 이용할 수 있다.따라서



-Bolierplate 코드가 줄어든다.



**단점**



-AppCompatActivity가 아니라 특정한 Activity를 상속해야만 하는 경우가 있다.
=> 어쩔 수 없이 BaseActivity를 쓰지 못한다.



-BaseActivity가 변경되면 이를 상속한 모든 Activity들이 변경되는 것이므로 부담이 크다. (Side Effect)



-공동작업을 할 때 다른 사람이 코드를 이해하기 어렵다.





이제 basecode예시 잘 들어놓은 블로그 링크를 걸어놓겠다 나중에 시도해봐야겠다 ㅎㅎ



[액티비티](https://seunghyun.in/android/1/)



[프래그먼트](https://youngest-programming.tistory.com/285)





### **3-2**

notifyDataSetChanged의 문제점!!



**리스트 업데이트 하는데 5가지 방법이있다**

리스트를 업데이트 하는방법중 가장 큰범위인 리스트의 크기와 아이템이 둘다 변경되는 경우에 사용하면 되는 것인

notifyDataSetChanged를 무지성으로 쓰면 모든경우에 다 적용이야 되겠지만 비효율적으로 움직일것이다.





그러므로 나머지 4가지 방법을 적재적소에 이용해서 자원을 아끼자



관련 방법들을 잘정리해 놓은 블로그가있어 링크로 남겨놓겠다



[**관련 함수설명 링크**](https://todaycode.tistory.com/55)





자근데 이방법 말고 더 참신한거 써보자



바로 DiffUtil 이다.



[https://velog.io/@deepblue/RecyclerView%EC%9D%98-notifyDataSetChanged](https://velog.io/@deepblue/RecyclerView의-notifyDataSetChanged)



이블로그에 있는거 그대로 구현해봤는데 뭔가 틀린부분이 분명있을거같다 급하게해서 돌아는가는데

어쨋든 추후에 다른자료들과 비교해보면서 체크해봐야겠다


출처:

https://kumgo1d.tistory.com/44

[https://velog.io/@jinny_0422/Android-Fragment-Activity%EA%B0%84-%EB%8D%B0%EC%9D%B4%ED%84%B0%EC%A0%84%EB%8B%AC](https://velog.io/@jinny_0422/Android-Fragment-Activity간-데이터전달)

https://dudmy.net/android/2018/05/02/drag-and-swipe-recyclerview/

https://seunghyun.in/android/1/

https://youngest-programming.tistory.com/285

https://todaycode.tistory.com/55

[https://velog.io/@deepblue/RecyclerView%EC%9D%98-notifyDataSetChanged](https://velog.io/@deepblue/RecyclerView의-notifyDataSetChanged)




</details>

<details markdown="1">
<summary>3주차</summary>
# -실행화면



https://user-images.githubusercontent.com/54737136/139484281-73079e46-c2de-47fb-91ce-a0d315c5cf1b.mp4



# -코드설명

level1다했고

level2,3 다했다.



## level2

2-1

NestedScrollableHost.kt

```
package changhwan.experiment.sopthomework

/*
 * Copyright 2019 The Android Open Source Project
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


import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.FrameLayout
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import kotlin.math.absoluteValue
import kotlin.math.sign

/**
 * Layout to wrap a scrollable component inside a ViewPager2. Provided as a solution to the problem
 * where pages of ViewPager2 have nested scrollable elements that scroll in the same direction as
 * ViewPager2. The scrollable element needs to be the immediate and only child of this host layout.
 *
 * This solution has limitations when using multiple levels of nested scrollable elements
 * (e.g. a horizontal RecyclerView in a vertical RecyclerView in a horizontal ViewPager2).
 */
class NestedScrollableHost : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    private var touchSlop = 0
    private var initialX = 0f
    private var initialY = 0f
    private val parentViewPager: ViewPager2?
        get() {
            var v: View? = parent as? View
            while (v != null && v !is ViewPager2) {
                v = v.parent as? View
            }
            return v as? ViewPager2
        }

    private val child: View? get() = if (childCount > 0) getChildAt(0) else null

    init {
        touchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    private fun canChildScroll(orientation: Int, delta: Float): Boolean {
        val direction = -delta.sign.toInt()
        return when (orientation) {
            0 -> child?.canScrollHorizontally(direction) ?: false
            1 -> child?.canScrollVertically(direction) ?: false
            else -> throw IllegalArgumentException()
        }
    }

    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        handleInterceptTouchEvent(e)
        return super.onInterceptTouchEvent(e)
    }

    private fun handleInterceptTouchEvent(e: MotionEvent) {
        val orientation = parentViewPager?.orientation ?: return

        // Early return if child can't scroll in same direction as parent
        if (!canChildScroll(orientation, -1f) && !canChildScroll(orientation, 1f)) {
            return
        }

        if (e.action == MotionEvent.ACTION_DOWN) {
            initialX = e.x
            initialY = e.y
            parent.requestDisallowInterceptTouchEvent(true)
        } else if (e.action == MotionEvent.ACTION_MOVE) {
            val dx = e.x - initialX
            val dy = e.y - initialY
            val isVpHorizontal = orientation == ORIENTATION_HORIZONTAL

            // assuming ViewPager2 touch-slop is 2x touch-slop of child
            val scaledDx = dx.absoluteValue * if (isVpHorizontal) .5f else 1f
            val scaledDy = dy.absoluteValue * if (isVpHorizontal) 1f else .5f

            if (scaledDx > touchSlop || scaledDy > touchSlop) {
                if (isVpHorizontal == (scaledDy > scaledDx)) {
                    // Gesture is perpendicular, allow all parents to intercept
                    parent.requestDisallowInterceptTouchEvent(false)
                } else {
                    // Gesture is parallel, query child if movement in that direction is possible
                    if (canChildScroll(orientation, if (isVpHorizontal) dx else dy)) {
                        // Child can scroll, disallow all parents to intercept
                        parent.requestDisallowInterceptTouchEvent(true)
                    } else {
                        // Child cannot scroll, allow all parents to intercept
                        parent.requestDisallowInterceptTouchEvent(false)
                    }
                }
            }
        }
    }
}
```

구글에서 복붙했다

```
<changhwan.experiment.sopthomework.NestedScrollableHost
    android:layout_width="match_parent"
    android:layout_height="0dp"
    app:layout_behavior="@string/appbar_scrolling_view_behavior"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintTop_toBottomOf="@+id/tl_fragment_home">

    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/vp_fragment_home"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</changhwan.experiment.sopthomework.NestedScrollableHost>
```

그후 NestedScrollableHost로 감싸줬다 내부에있는 viewpager2에



level2-2

처음에 그냥 data에 이렇게 uri추가해서 viewhilder로 Glide로 넣었었는데 databinding하면서 bindingadapter 로 해결했다

![image](https://user-images.githubusercontent.com/54737136/139485083-f5041625-c2f7-4def-93c6-29c7c1dabc24.png)



BindingAdapters.kt

```
package changhwan.experiment.sopthomework


import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("recyclerGlide")
    fun setImage (imageview : ImageView, url : MutableLiveData<String>){
        Glide.with(imageview.context)
            .load(url.value)
            .circleCrop()
            .into(imageview)
    }
}
```



follower_item.xml

```
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>

        <variable
            name="profileRecycler"
            type="changhwan.experiment.sopthomework.FollowerData" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:id="@+id/followerLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <ImageView
            android:id="@+id/imageView"
            android:layout_width="49dp"
            android:layout_height="0dp"
            android:layout_marginLeft="21dp"
            android:layout_marginTop="24dp"
            android:layout_marginBottom="24dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintDimensionRatio="1:1"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:recyclerGlide="@{profileRecycler.followerImg}" />

        <TextView
            android:id="@+id/followerName"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginLeft="22dp"
            android:layout_marginTop="25dp"
            android:fontFamily="@font/noto_sans_kr"
            android:includeFontPadding="false"
            android:text="@{profileRecycler.followerName}"
            android:textFontWeight="700"
            android:textSize="16sp"
            android:textStyle="normal"
            app:layout_constraintStart_toEndOf="@+id/imageView"
            app:layout_constraintTop_toTopOf="parent"
            tools:text="이름" />

        <TextView
            android:id="@+id/followerIntro"
            android:layout_width="150dp"
            android:layout_height="wrap_content"
            android:layout_marginTop="5dp"
            android:layout_marginBottom="24sp"
            android:ellipsize="end"
            android:fontFamily="@font/noto_sans_kr"
            android:includeFontPadding="false"
            android:maxLines="1"
            android:text="@{profileRecycler.followerIntro}"
            android:textFontWeight="400"
            android:textSize="14sp"
            android:textStyle="normal"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintStart_toStartOf="@+id/followerName"
            app:layout_constraintTop_toBottomOf="@+id/followerName"
            tools:text="자기소개" />
    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```



## level3

3-1

설명은 과제에서 배운거에서 다했습니다.

follower_Item.xml

```
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>

        <variable
            name="profileRecycler"
            type="changhwan.experiment.sopthomework.FollowerData" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:id="@+id/followerLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <ImageView
            android:id="@+id/imageView"
            android:layout_width="49dp"
            android:layout_height="0dp"
            android:layout_marginLeft="21dp"
            android:layout_marginTop="24dp"
            android:layout_marginBottom="24dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintDimensionRatio="1:1"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:recyclerGlide="@{profileRecycler.followerImg}" />

        <TextView
            android:id="@+id/followerName"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginLeft="22dp"
            android:layout_marginTop="25dp"
            android:fontFamily="@font/noto_sans_kr"
            android:includeFontPadding="false"
            android:text="@{profileRecycler.followerName}"
            android:textFontWeight="700"
            android:textSize="16sp"
            android:textStyle="normal"
            app:layout_constraintStart_toEndOf="@+id/imageView"
            app:layout_constraintTop_toTopOf="parent"
            tools:text="이름" />

        <TextView
            android:id="@+id/followerIntro"
            android:layout_width="150dp"
            android:layout_height="wrap_content"
            android:layout_marginTop="5dp"
            android:layout_marginBottom="24sp"
            android:ellipsize="end"
            android:fontFamily="@font/noto_sans_kr"
            android:includeFontPadding="false"
            android:maxLines="1"
            android:text="@{profileRecycler.followerIntro}"
            android:textFontWeight="400"
            android:textSize="14sp"
            android:textStyle="normal"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintStart_toStartOf="@+id/followerName"
            app:layout_constraintTop_toBottomOf="@+id/followerName"
            tools:text="자기소개" />
    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```





FollowerAdapter.kt

```
package changhwan.experiment.sopthomework

import android.annotation.SuppressLint
import android.content.Intent
import android.view.KeyEvent.ACTION_DOWN
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import changhwan.experiment.sopthomework.databinding.FollowerItemBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.processNextEventInCurrentThread

class FollowerAdapter(private val listener: ItemDragListener) :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(), ItemActionListener {

    var followerData = mutableListOf<FollowerData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
            FollowerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerData[position])
    }

    override fun getItemCount(): Int = followerData.size

    //diffUtill 부분 이상하면 나중에 바꿔야함
    fun setContact(contacts: List<FollowerData>) {
        val diffResult =
            DiffUtil.calculateDiff(ContactDiffUtil(this.followerData, followerData), false)
        diffResult.dispatchUpdatesTo(this)
        this.followerData = followerData
    }
    //여기까지 diffUtill

    //아이템 드래그 드롭
    override fun onItemMoved(from: Int, to: Int) {
        if (from == to) {
            return
        }

        val fromItem = followerData.removeAt(from)
        followerData.add(to, fromItem)
        notifyItemMoved(from, to)
    }

    override fun onItemSwiped(position: Int) {
        followerData.removeAt(position)
        notifyItemRemoved(position)
    }


    @SuppressLint("ClickableViewAccessibility")
    inner class FollowerViewHolder(
        private val binding: FollowerItemBinding,
        listener: ItemDragListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FollowerData) {
            binding.profileRecycler = data
            // binding.executePendingBindings() -> 없어도 된단다 이거 바인딩할때 작업들 당장당장 수행하라고 강요하는 함수. 그리고 lifecycle owner어따넣냐 안넣어 망할
        }

        init {
            binding.root.setOnClickListener {
                val intent = Intent(binding.root?.context, DetailActivity::class.java).apply {
                    this.putExtra("name", followerData[adapterPosition].followerName.value)
                    this.putExtra("src", R.drawable.pig)
                }
                startActivity(binding.root.context, intent, null)
            }

            binding.root.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    listener.onStartDrag(this)
                }
                false
            }
        }

    }
}
```



bindingadapter.kt



```
package changhwan.experiment.sopthomework


import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("recyclerGlide")
    fun setImage (imageview : ImageView, url : MutableLiveData<String>){
        Glide.with(imageview.context)
            .load(url.value)
            .circleCrop()
            .into(imageview)
    }
}
```





3-2

이것도 설명은 이번과제에서 배운것에 다했습니다.

사진은 데이터바인딩으로 넣었다 glide안쓰고 그냥 uri변수에 담고 변수바로 이미지뷰에 연결했다



fragment _camera.xml

```
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>
        <variable
            name="camera"
            type="changhwan.experiment.sopthomework.CameraData" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".CameraFragment">

        <ImageView
            android:id="@+id/camera_img"
            android:layout_width="160dp"
            android:layout_height="0dp"
            android:layout_marginTop="90dp"
            android:src="@{camera.picUri}"
            app:layout_constraintDimensionRatio="1"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            tools:src="@tools:sample/avatars" />

        <TextView
            android:id="@+id/camera_text"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="30dp"
            android:fontFamily="@font/noto_sans_kr"
            android:includeFontPadding="false"
            android:text="사진을 첨부해주세요"
            android:textColor="#333333"
            android:textFontWeight="700"
            android:textSize="20sp"
            app:layout_constraintEnd_toEndOf="@+id/camera_img"
            app:layout_constraintStart_toStartOf="@+id/camera_img"
            app:layout_constraintTop_toBottomOf="@+id/camera_img" />

        <Button
            android:id="@+id/camera_button"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_marginLeft="30dp"
            android:layout_marginTop="30dp"
            android:layout_marginRight="30dp"
            android:background="@drawable/button_border_pink"
            android:text="첨부하기"
            android:textColor="#FFFFFF"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/camera_text" />
    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```





CameraFragment.kt

```
package changhwan.experiment.sopthomework

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.DatabaseUtils
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
import changhwan.experiment.sopthomework.databinding.FragmentCameraBinding


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
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_camera, container, false)




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
```









# -이번과제를 통해 배운내용

## **라디오 버튼 커스텀**

selector의 state_checked 속성을 이용하기위해 그냥 버튼이아닌 라디오 버튼을 이용해서 버튼을 만들어줬다.





\- android:button="@null"


라디오 버튼에 동그라미 버튼 부분 없애려면 속성에 이렇게 넣어주면된다.



------

## fragment안의 fragment 처리



fragment안에서 fragment 처리할때는 activity에서 처리할때와 다르게 supportFragmentManager 를 사용하는것이 아닌



childFragmentManager를 사용해야한다.



또한 프래그먼트에서 부모의 프래그먼트 매니저를 접근하려면 ex)fragment1에서 activity의 fragment로 접근



이럴경우에는 parentFragmentManager를 사용한다.



https://ddangeun.tistory.com/127



자세한 설명은 이 블로그를 참고하자



------

## glide 추가 사항



dependency를 추가

```
implementation 'com.github.bumptech.glide:glide:4.11.0'
annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'
```



옵션없는 이미지로드

```
/* Activity에서 사용할 경우 */

Glide.with(this)
    .load(R.drawable.img_file_name)
    .into(imageView)
/* ViewHolder에서 사용할 경우 */

Glide.with(itemView)
    .load(R.drawable.img_file_name)
    .into(itemView.imageView)
```

뷰홀더에서는 itemview 을 binding.root로 대체하면된다.



level 2-2 처럼 그냥 이미지 url넣어주면 알아서 표시됨glide



각함수 설명

- with() : View, Fragment 혹은 Activity로부터 Context를 가져온다.
- load() : 이미지를 로드한다. 다양한 방법으로 이미지를 불러올 수 있다. (Bitmap, Drawable, String, Uri, File, ResourId(Int), ByteArray)
- into() : 이미지를 보여줄 View를 지정한다.
- placeholder() : Glide 로 이미지 로딩을 시작하기 전에 보여줄 이미지를 설정한다.
- error() : 리소스를 불러오다가 에러가 발생했을 때 보여줄 이미지를 설정한다.
- fallback() : load할 url이 null인 경우 등 비어있을 때 보여줄 이미지를 설정한다.
- skipMemoryCache() : 메모리에 캐싱하지 않으려면 true로 준다.
- diskCacheStrategy() : 디스크에 캐싱하지 않으려면 DiskCacheStrategy.NONE로 준다. 다음과 같은 옵션이 있다. (ALL, AUTOMATIC, DATA, NONE, RESOURCE)

gif로딩기능 있는데 이건 로티쓰는게 더 좋은거아님?





https://blog.yena.io/studynote/2020/06/10/Android-Glide.html



기타 참고사항은 이블로그를 참고하자

------

## ViewPager2 중첩스크롤 문제 해결하기

구글 공식문서에 나와있는 방법대로했다



https://developer.android.com/training/animation/vp2-migration?hl=ko



구체적인 방법은



1.**NestedScrollableHost.kt 파일추가**



https://github.com/android/views-widgets-samples/blob/master/ViewPager2/app/src/main/java/androidx/viewpager2/integration/testapp/NestedScrollableHost.kt



링크의 내용을 긁어서 **NestedScrollableHost.kt 를 추가시켜준다.**



**2.xml 에서 중첩되는 즉 내부의 스크롤뷰(viewpager2) 에 NestedScrollableHost 씌워주기**

```
    <androidx.coordinatorlayout.widget.CoordinatorLayout>

        <com.google.android.material.appbar.AppBarLayout
            android:id="@+id/appBarLayout">

            <androidx.appcompat.widget.Toolbar
                android:id="@+id/toolbar">

                <com.google.android.material.tabs.TabLayout
                    android:id="@+id/chipsLayout" />

            </androidx.appcompat.widget.Toolbar>

        </com.google.android.material.appbar.AppBarLayout>

        <com.nasrabadiam.widget.widget.NestedScrollableHost
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layout_behavior="@string/appbar_scrolling_view_behavior">

            <androidx.viewpager2.widget.ViewPager2
                android:id="@+id/chipsViewPager"
                android:layout_width="match_parent"
                android:layout_height="match_parent" />

        </com.nasrabadiam.widget.NestedScrollableHost>

    </androidx.coordinatorlayout.widget.CoordinatorLayout>
```

이런식으로

내부에 viewpager2를 NestedScrollableHost로 감싸준다.



이러면 끝!!!!



참고블로그:

https://medium.com/@nasrabadiam/support-nested-scrollable-elements-inside-viewpager2-59fa34978899



------

## dataBinding을 리사이클러 뷰에 적용하기



리펙토링을 해보자



\1. 당연히 gradle추가해주고

```
android {
    ...
    dataBinding {
        enabled = true
    }
}
```



2.리사이클러의 아이템뷰 의 xml을 <layout>으로 감싼다.





![img](https://blog.kakaocdn.net/dn/dJC8Sd/btrjlQyfq0u/7UDDKw9REKXB6PkgPvLLsK/img.png)





아이템을 감쌌다.



3.data variable 추가

layout안에 추가해준다

```
<data>

        <variable
            name="profileRecycler"
            type="changhwan.experiment.sopthomework.FollowerData" />
    </data>
```





4.view와 data를 @{}로 bind해준다.

```
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>

        <variable
            name="profileRecycler"
            type="changhwan.experiment.sopthomework.FollowerData" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:id="@+id/followerLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <ImageView
            android:id="@+id/imageView"
            android:layout_width="49dp"
            android:layout_height="0dp"
            android:layout_marginLeft="21dp"
            android:layout_marginTop="24dp"
            android:layout_marginBottom="24dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintDimensionRatio="1:1"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:recyclerGlide="@{profileRecycler.followerImg}" />

        <TextView
            android:id="@+id/followerName"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginLeft="22dp"
            android:layout_marginTop="25dp"
            android:fontFamily="@font/noto_sans_kr"
            android:includeFontPadding="false"
            android:text="@{profileRecycler.followerName}"
            android:textFontWeight="700"
            android:textSize="16sp"
            android:textStyle="normal"
            app:layout_constraintStart_toEndOf="@+id/imageView"
            app:layout_constraintTop_toTopOf="parent"
            tools:text="이름" />

        <TextView
            android:id="@+id/followerIntro"
            android:layout_width="150dp"
            android:layout_height="wrap_content"
            android:layout_marginTop="5dp"
            android:layout_marginBottom="24sp"
            android:ellipsize="end"
            android:fontFamily="@font/noto_sans_kr"
            android:includeFontPadding="false"
            android:maxLines="1"
            android:text="@{profileRecycler.followerIntro}"
            android:textFontWeight="400"
            android:textSize="14sp"
            android:textStyle="normal"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintStart_toStartOf="@+id/followerName"
            app:layout_constraintTop_toBottomOf="@+id/followerName"
            tools:text="자기소개" />
    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```



5.viewholder 클래스에서 binding객체에 데이터 담아준다.



onbind 함수에서 일일히 다 넣었던거 그냥 itemview에서 설정해놓은 데이터 변수에 매번 들어오는 data를 넣어주는거로 바꿔준다.



```
 inner class FollowerViewHolder(
        private val binding: FollowerItemBinding,
        listener: ItemDragListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FollowerData) {
            binding.profileRecycler = data
            // binding.executePendingBindings() -> 없어도 된단다 이거 바인딩할때 작업들 당장당장 수행하라고 강요하는 함수. 
        }
        //중략
 }
```



6.이미지 같은거 처리를위해 bindingadpter 만들어주기

```
package changhwan.experiment.sopthomework


import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("recyclerGlide")
    fun setImage (imageview : ImageView, url : MutableLiveData<String>){
        Glide.with(imageview.context)
            .load(url.value)
            .circleCrop()
            .into(imageview)
    }
}
```

기존에 bindingadapter 공부했던것처럼 처리 불가능한거 만들어준다. -> 이미지처리 이제 glide 추가했기에 그걸로 처리했다.



그래서 이미지뷰에서 이미지 넣는부분이 이렇다

```
app:recyclerGlide="@{profileRecycler.followerImg}"
```



결론적으로 layout 감싸주고 데이터 만들어주는곳은 item이고

viewholder에서 데이터 집어넣어주는형태이다



참고블로그:

https://salix97.tistory.com/244





------

## 갤러리에서 이미지 받아오기

옛날에 프로젝트 할때는 라이브러리 이용해서 크롭기능과 앨범접근까지해서 권한설정까지 다해서 편했는데 그냥간단하게 하려고 라이브러리 이용 안하려니 오히려 더복잡하다. 어쨋든 이미지 끌고오는거 자체는 간편하니 갤러리로 intent를 통해접근해서 1장 uri로 받아오는 것을 다뤄보려한다.





```
package changhwan.experiment.sopthomework

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.DatabaseUtils
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
import changhwan.experiment.sopthomework.databinding.FragmentCameraBinding


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
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_camera, container, false)




        binding.lifecycleOwner = this

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
```

우선 전체 코드는 이렇고 이미지는 uri형태로 받아와서 변수에 넣고 그변수를 그냥 바로 databinding을 통해서 넣어줬다.



쪼개서 보자면

```
 private lateinit var getContent: ActivityResultLauncher<Intent>
```

\1. getContent라는 변수 생성



나중에 이렇게 초기화해준다



```
private fun initPicUri(){


        getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val cameraData = CameraData(picUri = MutableLiveData<Uri>().apply { value = it.data?.data })
            binding.camera = cameraData
        }
    }
```



원래 구글에서는 startActivityForResult가 아닌 기본으로 제공해주는 Contrat 함수인 GetContent()를 쓰라 하지만 이걸 이용하면 불변한 UI IMAGE PICKER 가 뜨게된다 그래서 그냥 ActivityResultContracts.StartActivityForResult() 를 이용해서 앨범에 접근하였다.



2.인텐트 변수 만들기

```
private fun initIntent(){
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = MediaStore.Images.Media.CONTENT_TYPE
            type = "image/*"
        }
```



이렇게 intent를 설정해준다.



3.button에 눌리면 launch되도록 설정

```
 binding.cameraButton.setOnClickListener{
            var permission = ContextCompat.checkSelfPermission(fContext, Manifest.permission.READ_EXTERNAL_STORAGE)
            if(permission == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(requireActivity(),arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE)
            } else {
                getContent.launch(intent)
            }
        }
```



getContent.Launch(intent)를 통해서 앨범으로 넘어간다.



나머지 코드는 권한 관련코드이다 권한에 대한것은 밑에 알아볼것이다.



참고블로그:

https://youngest-programming.tistory.com/517



------

## 갤러리 접근 권한 설정

참고 액티비티와 프래그먼트에서 권한 받아오는게 은근 많이 다르다 이걸 좀 인지하고 가자 프래그먼트에서는 고려해야할것들이있다 -> 컨텍스트, 액티비티



갤러리 접근하려면 권한을 얻어야한다.

우선manifest에 storage 읽기쓰기 권한을 추가한다.

```
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
```



일단 자세하게 해야할것 모두 나와있는 블로그글과 간단하게 정리된 블로그글 하나씩 링크를 남겨놓는다.





복잡:

https://manorgass.tistory.com/74



간단:

https://superwony.tistory.com/101







적용은 간단한걸로 했다.



코드를 봐보자

```
 binding.cameraButton.setOnClickListener{
            var permission = ContextCompat.checkSelfPermission(fContext, Manifest.permission.READ_EXTERNAL_STORAGE)
            if(permission == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(requireActivity(),arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE)
            } else {
                getContent.launch(intent)
            }
        }
    }
```



ContextCompat.checkSelfPermission(fContext, Manifest.permission.READ_EXTERNAL_STORAGE)



이부분은 읽기 권한을 사용자에게 받았는지 안받았는지 가져오는 부분인데 첫번째 인자로 context를 넣어줘야한다.

근데 fragment는 context가 없기에 부모 액티비티의 context를 가져와야하는데 2가지방법이있다

------





-부제 fragment에서 context 가져오기

1.requireContext() 함수이용 이함수를 쓰면 getcontext 와는 다르게 notnull한 context를 반환한다.



2.onAttach 함수 오버라이딩해서 context 받아오기

onAttach의 인자로 부모의 context가 들어오기에 거기서 전역변수에 담아서 사용해도된다.





![img](https://blog.kakaocdn.net/dn/qlc6e/btrjkio485o/KARgM6RwRmKAd1qWwwAwC0/img.png)



------

어쨋든 이렇게 context 가져와서 첫번째 인자에넣고 두번쨰 인자에 무슨 권한인지 넣어주면 권한을 받은지 안받은지 여부를 알려준다.



그거를 조건문으로 권한 안받았으면 받는 코드를

이미 받아져있다면 바로 실행해주는 코드를 짜고



권한 받아오는 부분을 봐보자

```
 ActivityCompat.requestPermissions(requireActivity(),arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE)
```

이렇게

ActivityCompat.requestPermissions 를 사용하는데 첫번째 인자가 activity를 요구한다.

그래서 프래그 먼트에서 사용하는것을 검색해보니 그냥 requestPermissions를 이용하는 방법이나오는데 실행은 되지만 이건 deprecated 되었기에



프래그먼트의 액티비티를 가져올수있는 방법을 봐보자

------



-부제 fragment에서 부모 activity가져오기



requireActivity() 함수를 이용한다면 부모 Activity를 가져온다.

getActivity와 다른점은 notNull한 Activity를 반환한다.



------

그래서 이렇게 ActivityCompat.requestPermissions 를 이용해서 권한을 받아온다.
</details>


<details markdown="1">
<summary>4주차</summary>

# -실행화면

https://user-images.githubusercontent.com/54737136/141431364-6a3f1123-c3aa-421a-9347-1d5670c4b8a1.mp4


# -코드설명

level1 다했고 아물론 로그인과 회원가입만함

level2,3다함



설명은 이번과제를 통해 배운내용에 다적어놨다.



level1 sign in이 주라 sign in만 넣겠습니다 ㅎㅎ 지송

RequestSignInData.kt

```
package changhwan.experiment.sopthomework

data class RequestSignInData(
    val email : String,
    val password : String
)
```

ResponseSigninData

```
package changhwan.experiment.sopthomework

import android.provider.ContactsContract

data class ResponseSignInData(
    val id: Int,
    val name: String,
    val email: String
)
```

SignInService.kt

```
package changhwan.experiment.sopthomework

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignInService {
    @POST("user/login")
    suspend fun postSignIn(
        @Body body: RequestSignInData
    ):Response<ResponseWrapper<ResponseSignInData>>
}
```



serviceCreator.kt

```
package changhwan.experiment.sopthomework

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

    private val headerInterceptor = Interceptor{
        val request = it.request()
            .newBuilder()
            .addHeader("Content-Type","application/json")
            .build()
        return@Interceptor it.proceed(request)
    }

    val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .build()

    private const val  SOPT_BASE_URL= "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"

    private val SoptRetrofit :Retrofit = Retrofit.Builder()
        .baseUrl(SOPT_BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val signUpService :SignUpService = SoptRetrofit.create(SignUpService::class.java)
    val signInService :SignInService = SoptRetrofit.create(SignInService::class.java)


    private const val  GITHUB_BASE_URL="https://api.github.com/"

    private  val GitHubRetrofit : Retrofit = Retrofit.Builder()
        .baseUrl(GITHUB_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val gitHubService :GitHubService = GitHubRetrofit.create(GitHubService::class.java)
}
```

SignViewModel.kt

```
package changhwan.experiment.sopthomework


import android.net.Network
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext


class SignViewModel : ViewModel() {

    private val _viewEmail = MutableLiveData<String>()
    private val _viewName = MutableLiveData<String>()
    private val _viewPassword = MutableLiveData<String>()
    private val _conSuccess = MutableLiveData<Boolean>()

    val viewEmail: LiveData<String>
        get() = _viewEmail
    val viewName: LiveData<String>
        get() = _viewName
    val viewPassword: LiveData<String>
        get() = _viewPassword
    val conSuccess: LiveData<Boolean>
        get() = _conSuccess


    fun getEmail(email: String) {
        _viewEmail.value = email
    }

    fun getName(name: String) {
        _viewName.value = name
    }

    fun getPassword(password: String) {
        _viewPassword.value = password
    }

    fun startSignUp() {
        val requestSignUpData = RequestSignUpData(
            email = _viewEmail.value!!,
            name = _viewName.value!!,
            password = _viewPassword.value!!
        )

        val call: Call<ResponseSignUpData> =
            ServiceCreator.signUpService.postSignUp(requestSignUpData)

        call.enqueue(object : Callback<ResponseSignUpData> {
            override fun onResponse(
                call: Call<ResponseSignUpData>,
                response: Response<ResponseSignUpData>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data

                    if (data != null) {
                        _viewName.value = data.name
                        _viewEmail.value = data.email
                    }

                    _conSuccess.value = true

                } else {
                    _conSuccess.value = false
                }
            }

            override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                _conSuccess.value = false
            }
        })
    }


    fun startSignIn() {
        val requestSignInData = RequestSignInData(
            email = _viewEmail.value!!,
            password = _viewPassword.value!!
        )

//        val call : Call<ResponseSignInData> = ServiceCreator.signInService.postSignIn(requestSignInData)
//
//        call.enqueue(object : Callback<ResponseSignInData>{
//            override fun onResponse(
//                call: Call<ResponseSignInData>,
//                response: Response<ResponseSignInData>
//            ) {
//                val data = response.body()?.data
//
//                if(response.isSuccessful){
//                    if (data != null) {
//                        _viewName.value = data.name
//                    }
//                    _conSuccess.value = true
//                } else {
//                    _conSuccess.value = false
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseSignInData>, t: Throwable) {
//                _conSuccess.value = false
//            }
//
//        })

        viewModelScope.launch {
            val response = ServiceCreator.signInService.postSignIn(requestSignInData)
            val data = response.body()?.data

            if (response.isSuccessful) {
                if (data != null) {
                    _viewName.value = data.name
                }
                _conSuccess.value = true
            } else {
                _conSuccess.value = false
            }
        }
    }
}
```

SignInActivity

```
package changhwan.experiment.sopthomework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import changhwan.experiment.sopthomework.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var  getResult : ActivityResultLauncher<Intent>
    private val signInViewModel by viewModels<SignViewModel>()
    val signInEmail = MutableLiveData<String>()
    val signInPassword = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_in)
        binding.signInData = this
        binding.lifecycleOwner = this

        startLogin()
        startSignUp()
        observeSuccess()
    }

    private fun startLogin(){
        binding.loginButton.setOnClickListener {
            signInEmail.value?.let { signInViewModel.getEmail(it) }
            signInPassword.value?.let { signInViewModel.getPassword(it) }
            signInViewModel.startSignIn()

        }
    }

    private fun observeSuccess(){
        signInViewModel.conSuccess.observe(this, Observer {
            if (signInViewModel.conSuccess.value == true) {
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "${signInViewModel.viewName.value}님 환영합니다", Toast.LENGTH_SHORT).show()
            } else {
                binding.inEditId.text.clear()
                binding.inEditPw.text.clear()
                Toast.makeText(this, "로그인실패", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun startSignUp(){
        binding.signUpButton.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            getResult.launch(intent)
        }

        getResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK) {
                binding.inEditId.text.clear()
                binding.inEditId.text.append(it.data?.getStringExtra("Id"))
                binding.inEditPw.text.clear()
                binding.inEditPw.text.append(it.data?.getStringExtra("Pw"))
            }
        }
    }



}
```





level2-1

ResponseGitHubFollowerData.kt

```
package changhwan.experiment.sopthomework

data class ResponseGitHubFollowerData(
    val login: String,
    val id: Int,
    val node_id: String,
    val avatar_url: String,
    val gravatar_id: String,
    val url: String,
    val html_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val starred_url: String,
    val subscriptions_url: String,
    val organizations_url: String,
    val repos_url: String,
    val events_url: String,
    val received_events_url: String,
    val type: String,
    val site_admin: Boolean,
)
```

ResponseGitHubUserData.kt

```
package changhwan.experiment.sopthomework

data class ResponseGithubUserData(
    val login: String,
    val id: Int,
    val node_id: String,
    val avatar_url: String,
    val gravatar_id: String,
    val url: String,
    val html_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val starred_url: String,
    val subscriptions_url: String,
    val organizations_url: String,
    val repos_url: String,
    val events_url: String,
    val received_events_url: String,
    val type: String,
    val site_admin: Boolean,
    val name: String,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val hireable: String?,
    val bio: String?,
    val twitter_username: String?,
    val public_repos: Int?,
    val public_gists: Int?,
    val followers: Int,
    val following: Int,
    val created_at: String,
    val updated_at: String
)
```

GitHubService.kt

```
package changhwan.experiment.sopthomework

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GitHubService {

    @GET("users/{userId}/followers")
    fun getGitHubFollowers(
        @Path("userId") userId:String
    ): Call<List<ResponseGitHubFollowerData>>

    @GET("users/{userId}")
    fun getGitHubUsers(
        @Path("userId") userId:String
    ): Call<ResponseGithubUserData>
}
```

serviceCreator.kt

```
package changhwan.experiment.sopthomework

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

    private val headerInterceptor = Interceptor{
        val request = it.request()
            .newBuilder()
            .addHeader("Content-Type","application/json")
            .build()
        return@Interceptor it.proceed(request)
    }

    val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .build()

    private const val  SOPT_BASE_URL= "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"

    private val SoptRetrofit :Retrofit = Retrofit.Builder()
        .baseUrl(SOPT_BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val signUpService :SignUpService = SoptRetrofit.create(SignUpService::class.java)
    val signInService :SignInService = SoptRetrofit.create(SignInService::class.java)


    private const val  GITHUB_BASE_URL="https://api.github.com/"

    private  val GitHubRetrofit : Retrofit = Retrofit.Builder()
        .baseUrl(GITHUB_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val gitHubService :GitHubService = GitHubRetrofit.create(GitHubService::class.java)
}
```

GitHubViewModel.kt

```
package changhwan.experiment.sopthomework

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubViewModel : ViewModel() {

    private val _followerList = mutableListOf<MutableLiveData<String>>()
    private val _followerAvatarUrl = mutableListOf<MutableLiveData<String>>()
    private val _bio = mutableListOf<MutableLiveData<String>>()
    private val _conclusionData = mutableListOf<FollowerData>()
    private val _getFollowerDataDone = MutableLiveData<Event<String>>()
    private val _getUserDataDone = MutableLiveData<Event<String>>()
    private val _getConclusionDataDone = MutableLiveData<Event<String>>()

    val followerList: List<MutableLiveData<String>>
        get() = _followerList
    val followerAvatarUrl: List<MutableLiveData<String>>
        get() = _followerAvatarUrl
    val bio: List<MutableLiveData<String>>
        get() = _bio
    val conclusionData: List<FollowerData>
        get() = _conclusionData
    val getFollowerDataDone: LiveData<Event<String>>
        get() = _getFollowerDataDone
    val getUserDataDone: LiveData<Event<String>>
        get() = _getUserDataDone
    val getConclusionDataDone: LiveData<Event<String>>
        get() = _getConclusionDataDone

    fun getGitHubFollowerData() {
        val call: Call<List<ResponseGitHubFollowerData>> =
            ServiceCreator.gitHubService.getGitHubFollowers("2chang5")

        call.enqueue(object : Callback<List<ResponseGitHubFollowerData>> {
            override fun onResponse(
                call: Call<List<ResponseGitHubFollowerData>>,
                response: Response<List<ResponseGitHubFollowerData>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        _followerList.clear()
                        _followerAvatarUrl.clear()
                        for (i in data) {
                            _followerList.add(MutableLiveData<String>().apply { value = i.login })
                            _followerAvatarUrl.add(MutableLiveData<String>().apply { value = i.avatar_url })
                        }
                    }
                    _getFollowerDataDone.value = Event("followerDone")
                }
            }

            override fun onFailure(call: Call<List<ResponseGitHubFollowerData>>, t: Throwable) {

            }

        })
    }

    fun getGitHubUserData() {
        for (i in _followerList) {
            val call: Call<ResponseGithubUserData>? = i.value?.let {
                ServiceCreator.gitHubService.getGitHubUsers(
                    it
                )
            }

            if (call != null) {
                call.enqueue(object : Callback<ResponseGithubUserData> {
                    override fun onResponse(
                        call: Call<ResponseGithubUserData>,
                        response: Response<ResponseGithubUserData>
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body()?.bio

                            _bio.add(MutableLiveData<String>().apply { value = data })
                            if(i == _followerList.last()){
                                _getUserDataDone.value = Event("UserDone")
                            }
                        } else{

                        }

                    }

                    override fun onFailure(call: Call<ResponseGithubUserData>, t: Throwable) {

                    }
                })
            }
        }
    }


    fun getConclusionData() {
        _conclusionData.clear()
        for (i in _followerList.indices) {
            _conclusionData.add(
                FollowerData(
                    followerName = _followerList[i],
                    followerImg = _followerAvatarUrl[i],
                    followerIntro = _bio[i]
                    )
            )
        }
        _getConclusionDataDone.value = Event("ConclusionDone")
    }
}
```

FollowerFragmnet.kt

```
package changhwan.experiment.sopthomework

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import changhwan.experiment.sopthomework.databinding.FragmentFollowerBinding


class FollowerFragment : Fragment(), ItemDragListener {

    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private lateinit var followerAdapter: FollowerAdapter
    private lateinit var itemTouchHelper : ItemTouchHelper
    private val gitHubViewModel by activityViewModels<GitHubViewModel>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        gitHubViewModel.getGitHubFollowerData()
        gitHubViewModel.getFollowerDataDone.observe(viewLifecycleOwner, EventObserver{
            gitHubViewModel.getGitHubUserData()
        })
        gitHubViewModel.getUserDataDone.observe(viewLifecycleOwner, EventObserver{
            gitHubViewModel.getConclusionData()
        })

        _binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gitHubViewModel.getConclusionDataDone.observe(viewLifecycleOwner,EventObserver{
            siteFollowerRecycler()

            binding.followerRecycle.addItemDecoration(CustomMarginDecoration(24))
            binding.followerRecycle.addItemDecoration(CustomDividerDecoration(1f,10f, resources.getColor(R.color.divider),40))

            itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(followerAdapter))
            itemTouchHelper.attachToRecyclerView(binding.followerRecycle)
        })

    }

    fun siteFollowerRecycler(){
        followerAdapter = FollowerAdapter(this)

        binding.followerRecycle.adapter = followerAdapter

        followerAdapter.followerData.clear()

        followerAdapter.followerData.addAll(
            gitHubViewModel.conclusionData
        )



        //diffUtill부분 원래는 followerAdapter.notifyDataSetChanged()였음
        followerAdapter.setContact(followerAdapter.followerData)
        //여기까지
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
```



2-2

ServiceCreator.kt

```
package changhwan.experiment.sopthomework

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

    private val headerInterceptor = Interceptor{
        val request = it.request()
            .newBuilder()
            .addHeader("Content-Type","application/json")
            .build()
        return@Interceptor it.proceed(request)
    }

    val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .build()

    private const val  SOPT_BASE_URL= "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"

    private val SoptRetrofit :Retrofit = Retrofit.Builder()
        .baseUrl(SOPT_BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val signUpService :SignUpService = SoptRetrofit.create(SignUpService::class.java)
    val signInService :SignInService = SoptRetrofit.create(SignInService::class.java)


    private const val  GITHUB_BASE_URL="https://api.github.com/"

    private  val GitHubRetrofit : Retrofit = Retrofit.Builder()
        .baseUrl(GITHUB_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val gitHubService :GitHubService = GitHubRetrofit.create(GitHubService::class.java)
}
```



2-3

ResponseWrapper.kt

```
package changhwan.experiment.sopthomework

data class ResponseWrapper<T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T?
)
```



ResponseSignInData.kt

```
package changhwan.experiment.sopthomework

import android.provider.ContactsContract

data class ResponseSignInData(
    val id: Int,
    val name: String,
    val email: String
)
```

SignInService.kt

```
package changhwan.experiment.sopthomework

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignInService {
    @POST("user/login")
    suspend fun postSignIn(
        @Body body: RequestSignInData
    ):Response<ResponseWrapper<ResponseSignInData>>
}
```



3

SignInService.kt

```
package changhwan.experiment.sopthomework

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignInService {
    @POST("user/login")
    suspend fun postSignIn(
        @Body body: RequestSignInData
    ):Response<ResponseWrapper<ResponseSignInData>>
}
```



SignViewModel.kt

```
package changhwan.experiment.sopthomework


import android.net.Network
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext


class SignViewModel : ViewModel() {

    private val _viewEmail = MutableLiveData<String>()
    private val _viewName = MutableLiveData<String>()
    private val _viewPassword = MutableLiveData<String>()
    private val _conSuccess = MutableLiveData<Boolean>()

    val viewEmail: LiveData<String>
        get() = _viewEmail
    val viewName: LiveData<String>
        get() = _viewName
    val viewPassword: LiveData<String>
        get() = _viewPassword
    val conSuccess: LiveData<Boolean>
        get() = _conSuccess


    fun getEmail(email: String) {
        _viewEmail.value = email
    }

    fun getName(name: String) {
        _viewName.value = name
    }

    fun getPassword(password: String) {
        _viewPassword.value = password
    }

    fun startSignUp() {
        val requestSignUpData = RequestSignUpData(
            email = _viewEmail.value!!,
            name = _viewName.value!!,
            password = _viewPassword.value!!
        )

        val call: Call<ResponseSignUpData> =
            ServiceCreator.signUpService.postSignUp(requestSignUpData)

        call.enqueue(object : Callback<ResponseSignUpData> {
            override fun onResponse(
                call: Call<ResponseSignUpData>,
                response: Response<ResponseSignUpData>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.data

                    if (data != null) {
                        _viewName.value = data.name
                        _viewEmail.value = data.email
                    }

                    _conSuccess.value = true

                } else {
                    _conSuccess.value = false
                }
            }

            override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                _conSuccess.value = false
            }
        })
    }


    fun startSignIn() {
        val requestSignInData = RequestSignInData(
            email = _viewEmail.value!!,
            password = _viewPassword.value!!
        )

//        val call : Call<ResponseSignInData> = ServiceCreator.signInService.postSignIn(requestSignInData)
//
//        call.enqueue(object : Callback<ResponseSignInData>{
//            override fun onResponse(
//                call: Call<ResponseSignInData>,
//                response: Response<ResponseSignInData>
//            ) {
//                val data = response.body()?.data
//
//                if(response.isSuccessful){
//                    if (data != null) {
//                        _viewName.value = data.name
//                    }
//                    _conSuccess.value = true
//                } else {
//                    _conSuccess.value = false
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseSignInData>, t: Throwable) {
//                _conSuccess.value = false
//            }
//
//        })

        viewModelScope.launch {
            val response = ServiceCreator.signInService.postSignIn(requestSignInData)
            val data = response.body()?.data

            if (response.isSuccessful) {
                if (data != null) {
                    _viewName.value = data.name
                }
                _conSuccess.value = true
            } else {
                _conSuccess.value = false
            }
        }
    }
}
```





# -이번과제를 통해 배운내용

## **ViewModel관련**

과제 4주차 보면 알듯이

뷰모델에서 비동기적으로 정보 끌어오는거 처리해서 가져와서 저장했었고 그에 따른 처리

인텐트나 토스트 메세지 등등은 액티비티에서 실행했다



근데 정보가져오는 버튼 리스너 에서 뷰모델의 비동기처리를하고 동시에

정보가져오는거 뿌려주는걸하면 비동기처리가 끝나기도 전에 실행해서

nullpointexception이나 내가 원하는걸 처리하지 못하는 일이 발생했다.



그래도 토스트메세지나 인텐트같은 액티비티에서 처리해야할것들은 따로있기에 방법을 생각해서

비동기 완료 여부를 나타내는 변수를 boolean형태로 라이브데이터로 놓고 옵저버를 달아서 변화가있을경우 처리하도록했다 그리고 비동기처리 성공여부 200인지 400인지는 변수에 true/false여부로 판단하였다.



그래서 해결은 했지만 뭔가 더 좋은 방법이없나싶어 문다빈에게 물어봤는데

맞는 방법인데 boolean형태로 실제 사용되는 데이터 값이아닌 이벤트처리에만 사용되는 변수는 Event wapper개념을

사용하는것이 맞다고한다

-> 추후에 관련 정리글을 써야겠다.

[https://medium.com/prnd/mvvm%EC%9D%98-viewmodel%EC%97%90%EC%84%9C-%EC%9D%B4%EB%B2%A4%ED%8A%B8%EB%A5%BC-%EC%B2%98%EB%A6%AC%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95-6%EA%B0%80%EC%A7%80-31bb183a88ce](https://medium.com/prnd/mvvm의-viewmodel에서-이벤트를-처리하는-방법-6가지-31bb183a88ce)

관련 블로그글이다 또한 더욱 발전한 내용들도있으니 참고하자



또한 로그인할때 edittext에 담긴 text같은것들은 sharedPreference에 저장해서 가지고 오는것이 좋다고한다.

이부분도 참고해서 수정해보자



또한 이런부분에서 뷰모델에서 비동기처리가 끝난후 액티비티에서 코드를 처리하는것에 다른 좋은 방법이 없나 물어본결과

StateFlow라는 코루틴 개념중에 하나가 있는데 너무어려울것이니 나중에 사용해보라는 조언이있었다.

------

## 2-1 github api 적용하기

시행 착오가 정말 많았다 여기서 가져가야할것들을 살펴보자

1.get사용방법

```
package changhwan.experiment.sopthomework

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GitHubService {

    @GET("users/{userId}/followers")
    fun getGitHubFollowers(
        @Path("userId") userId:String
    ): Call<List<ResponseGitHubFollowerData>>

    @GET("users/{userId}")
    fun getGitHubUsers(
        @Path("userId") userId:String
    ): Call<ResponseGithubUserData>
}
```

이런식으로 인터페이스 하나에 여러개의 http메서드 넣을수있고 중간에 path 부분을 끼워넣을수있다

"users/{userId}/followers" 예시처럼





2.여러개의 api사용해서 baseurl여러개인경우

```
package changhwan.experiment.sopthomework

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val  SOPT_BASE_URL= "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"

    private val SoptRetrofit :Retrofit = Retrofit.Builder()
        .baseUrl(SOPT_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val signUpService :SignUpService = SoptRetrofit.create(SignUpService::class.java)
    val signInService :SignInService = SoptRetrofit.create(SignInService::class.java)


    private const val  GITHUB_BASE_URL="https://api.github.com/"

    private  val GitHubRetrofit : Retrofit = Retrofit.Builder()
        .baseUrl(GITHUB_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val gitHubService :GitHubService = GitHubRetrofit.create(GitHubService::class.java)
}
```

retrofit 객체 두개만들어서 그냥 쓰면된다.





3.서버통신할때는 앞전 데이터 불러오는 작업이 끝나고 다음것을 불러오도록(앞데이터가 뒤쪽에 불러오는것을 관여할때) 잘 로직을 짜야한다 머리를 굴리자



## **Log**

-> 또한 이럴때 잘안되면



![img](https://blog.kakaocdn.net/dn/bvmJVh/btrktc2dY7t/AvcKE6f8UYFDJv1yeM8c4K/img.png)



Log.d를 이용해서 찍어보자





![img](https://blog.kakaocdn.net/dn/byFTn3/btrkqHPcSa1/fbBmk3gMLNwBIuSHZbLW9K/img.png)



이렇게 검색해서 봐라

------

## **오류처리**



그리고 오류나면



logcat에서



![img](https://blog.kakaocdn.net/dn/EGGTr/btrkxPK461v/hGxHbDsmHQeOmplVhjZXL1/img.png)



error로 선택하고 exception놓으면 오류 뭔지 나온다 그거보고 해결해보자

------





이번에 서버통신하며 데이터 가져오면서 골치 아팠던거

```
fun getGitHubUserData() {
        for (i in _followerList) {
            val call: Call<ResponseGithubUserData>? = i.value?.let {
                ServiceCreator.gitHubService.getGitHubUsers(
                    it
                )
            }

            if (call != null) {
                call.enqueue(object : Callback<ResponseGithubUserData> {
                    override fun onResponse(
                        call: Call<ResponseGithubUserData>,
                        response: Response<ResponseGithubUserData>
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body()?.bio

                            _bio.add(MutableLiveData<String>().apply { value = data })
                            if(i == _followerList.last()){
                                _getUserDataDone.value = Event("UserDone")
                            }
                        } else{

                        }

                    }

                    override fun onFailure(call: Call<ResponseGithubUserData>, t: Throwable) {

                    }
                })
            }
        }
    }
```

getUserDataDone 업데이트 시기 첫번째 비동기처리나 아니면 for문 끝에 붙여놓으면 문제있었음 비동기라 먼저 처리되서

뒤쪽 코드에서 오류났음 그래서 마지막 비동기 처리에서 바꿀수있도록

```
if(i == _followerList.last()){
   _getUserDataDone.value = Event("UserDone")
}
```

이부분을 추가해서 마지막 원소일때 Event의 변화가 오도록했다.





그리고 Event를 쓴다고 라이브데이터가 변화를 안하는게 아니다 null값으로 계속 업데이트되고

그에따라 이벤트 계속 발생한다.

-> 리스트가 계속 추가되는 문제가 생겼었다.

해결방법

매번 list업데이트 하기전 clear하도록 죄다 clear붙여줬다. 그럼 초기화후 들어가므로 괜찮아졌다.



![img](https://blog.kakaocdn.net/dn/G253J/btrkqHhoS5D/ohPTXU5U3YUJoP1vNGjc0K/img.png)





------

## **OKHTTP3로 header 자동으로 추가해주기**



**okhttp에 interceptor로 중간에서 처리를해서 서버로 보낼수있다.**



우선 gradle을 추가해주고

```
//okhttp3
    implementation 'com.squareup.okhttp3:okhttp:3.14.9'
    implementation 'com.squareup.okhttp3:logging-interceptor:3.11.0'
```



다음은 실제 객체 구현해주는 부분에서

okhttp 객체도 구현해주고

interceptor도 만들어서

retrofit 객체를 만들때 client에다가 추가해주면된다.



header추가외에도 더 많은 기능이있다 추후에 찾아봐야겠다.

```
object ServiceCreator {

    private val headerInterceptor = Interceptor{
        val request = it.request()
            .newBuilder()
            .addHeader("Content-Type","application/json")
            .build()
        return@Interceptor it.proceed(request)
    }

    val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .build()

    private const val  SOPT_BASE_URL= "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"

    private val SoptRetrofit :Retrofit = Retrofit.Builder()
        .baseUrl(SOPT_BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val signUpService :SignUpService = SoptRetrofit.create(SignUpService::class.java)
    val signInService :SignInService = SoptRetrofit.create(SignInService::class.java)
```

interceptor에 addHeader를 통해 헤더를 다는부분을 만들어주고 빌드한후



OkHttpClient도 아까 만든 interceptor를 addInterceptor를 통해 추가해서 build해준후



retrofit 객체만들때 client에 추가해준다.





참고 블로그

https://hwanine.github.io/android/Retrofit-OkHttp/

[ Android - Retrofit2, OkHttp를 함께 사용하여 더욱 간결한 RestAPI 연동 (Kotlin) (2)Retrofit과 OkHttp를 함께 사용하여 더욱 간결하게 네이버 RestAPI와의 연동하는 과정을 소개하겠습니다.hwanine.github.io](https://hwanine.github.io/android/Retrofit-OkHttp/)

------

## **2-3 Wrapper 클래스**



앞쪽에 계속 중복되는 부분들 어떻게 처리할수있는가?



어짜피 없애려는 부분은 계속해서 같은게 나올것이다 그거 미리 wrapper class 를 통해서 작성해놓고



나머지 다른부분만 새로 작성해서 넣어주는 방식으로 처리한다.



과제에서는 signin 부분만 적용했다.





![img](https://blog.kakaocdn.net/dn/burBvA/btrkHnuHhEv/r0kgCA3vH3QTqeIEAkH9SK/img.png)



이걸 고치는것이다.



ResponseWrapper.kt

```
package changhwan.experiment.sopthomework

data class ResponseWrapper<T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T?
)
```



이렇게 미리 wrapper class로 중복된부분을 작성해놓는다.





ResponseSignInData.kt

```
package changhwan.experiment.sopthomework

import android.provider.ContactsContract

data class ResponseSignInData(
    val id: Int,
    val name: String,
    val email: String
)
```

다음 이렇게 오는 정보들 안에다 넣어줄꺼 작성해서



사용할때는 하나로 합쳐서 넣어준다.



SignInService.kt

```
package changhwan.experiment.sopthomework

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignInService {
    @POST("user/login")
    suspend fun postSignIn(
        @Body body: RequestSignInData
    ):Response<ResponseWrapper<ResponseSignInData>>
}
```



```
<ResponseWrapper<ResponseSignInData>>
```

요런식으로 사용하는것이다.





------

## **3-3 coroutine**

진짜진짜 맛배기로만해서 뭐가 맞는지도 잘모른다. 나중에 추후 공부를 더할거지만

실행도 잘되긴하고 여기저기 물어본결과 맞는방식인거같다.







사용방법은 의외로 간단하다 view model을 써서 viewmodelscope를 사용해서 잡다한거 알아서 되서 쉬웠던것도 있는거같다.



\1. gradle 추가

```
// ViewModel coroutine 스코프를 위한거
implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.1.0"
     
//coroutine
implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3'
```



2.suspend키워드 사용할 함수에 달아주기 + Call객체가아닌 Response객체 가져오는걸로 바꿔주기



SignInService.kt

```
package changhwan.experiment.sopthomework

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignInService {
    @POST("user/login")
    suspend fun postSignIn(
        @Body body: RequestSignInData
    ):Response<ResponseWrapper<ResponseSignInData>>
}
```



postSignIn 함수에 suspend 키워드를 달아줬다.

그리고 Call객체를 반환하는것이아닌 Response객체를 반환하는걸로 바꿔줬다.



3.이제 callback에서 처리하던거 대체하기



원래 callback 으로 차리하면 코드가 이랬다.

```
 fun startSignIn() {
        val requestSignInData = RequestSignInData(
            email = _viewEmail.value!!,
            password = _viewPassword.value!!
        )

        val call : Call<ResponseSignInData> = ServiceCreator.signInService.postSignIn(requestSignInData)

        call.enqueue(object : Callback<ResponseSignInData>{
            override fun onResponse(
                call: Call<ResponseSignInData>,
                response: Response<ResponseSignInData>
            ) {
                val data = response.body()?.data

                if(response.isSuccessful){
                    if (data != null) {
                        _viewName.value = data.name
                    }
                    _conSuccess.value = true
                } else {
                    _conSuccess.value = false
                }
            }

            override fun onFailure(call: Call<ResponseSignInData>, t: Throwable) {
                _conSuccess.value = false
            }

        })
}
```



근데 이거 액티비티면 기타등등 사전작업들을 하고 사용해야하는데

그냥 viewmodel내라서 viewModelScope를 사용해서

그냥 viewModelScope.launch{}내부에 불러오는거랑 그다음에 데이터 불러온거 처리하는 로직 넣어주면 비동기로 알아서 처리한다.

```
viewModelScope.launch {
            val response = ServiceCreator.signInService.postSignIn(requestSignInData)
            val data = response.body()?.data

            if (response.isSuccessful) {
                if (data != null) {
                    _viewName.value = data.name
                }
                _conSuccess.value = true
            } else {
                _conSuccess.value = false
            }
        }
```

이렇게 처리하면된다 코드 간결해진다 진짜.



그리고 response는 아까 인터페이스에서 반환하는거 Response로 바꿔놨으니 이제 저렇게 받아오면 Response를 받아와진다

그래서 그중원하는거 body값 에 data 빼와서 처리해주면된다.





참고블로그

https://enant.tistory.com/24



https://enant.tistory.com/23   <-근데 이부분은 쓸모가없었다 사실 그냥 이런느낌만 가지는용도로 보면된다.



https://developer88.tistory.com/214


</details>