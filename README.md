# Android-Changhwan
![github_이창환_ver1-24](https://user-images.githubusercontent.com/70698151/135754493-6025ae3d-c4d2-4181-8359-1d921f91d59e.png)

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

