package com.rengwuxian.composetodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rengwuxian.composetodo.data.DataCenter
import com.rengwuxian.composetodo.data.Todo
import com.rengwuxian.composetodo.ui.theme.ComposeTodoTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent { // 进入「Compose 模式」
      ComposeTodoTheme { // Lambda
        MainScreen()
      } // Gap Buffer
    }
  }
}
// 你吃饭了没？我还没吃，____吃了的话就别吃了。
// 111111111_________1111111
// 你吃没，了。
// onMeasure()
// ViewGroup.onMeasure(): 调用每个子 View 的 onMeasure(); 调用自己的 onMeasure()

// Intrinsic Measurement 固有特性测量：（固有尺寸测量）



// mutableStateOf(1) -> Int 1;2;3
// MutableState
// StateObject => StateRecord(firstStateRecord:3) -> StateRecord(2) -> StateRecord(1)
// Snapshot
// 1
// 2
// 3

@Composable
private fun MainScreen() {
  var inputting by remember { mutableStateOf(false) } // MutableState
  val animatedFabScale by animateFloatAsState(
    if (inputting) 0f else 1f
  )
  val animatedInputScale by animateFloatAsState(
    if (inputting) 1f else 0f
  )
  Scaffold(floatingActionButton = {
    FloatingActionButton(onClick = {
      // 弹出输入框
      inputting = true
    }, Modifier.scale(animatedFabScale)) {
      Icon(Icons.Filled.Add, "添加")
    }
  }, topBar = {
    TopAppBar {
      Icon(Icons.Filled.Check, "随便")
    }
  }) {
    Box(Modifier.fillMaxSize()) {
      Todos(DataCenter.todos)
      // LinearLayout layout_weight="1"
      Row(
        Modifier
          .align(Alignment.BottomCenter)
          .fillMaxWidth()
          .scale(animatedInputScale)
      ) {
        TextField(value = "", onValueChange = {}, Modifier.weight(1f))
        IconButton(onClick = {
          // 关闭输入框
          inputting = false
        }) {
          Icon(Icons.Filled.Send, "确认")
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
  MainScreen()
}

@Composable
private fun Todos(todos: List<Todo>) {
  Surface(
    Modifier.padding(16.dp), elevation = 8.dp,
    shape = RoundedCornerShape(8.dp)
  ) {
    // Unidirectional Data Flow
    LazyColumn(Modifier.fillMaxWidth()) {
      items(todos) { todo ->
        // LinearLayout
        Row(verticalAlignment = Alignment.CenterVertically) {
          Checkbox(checked = todo.completed, onCheckedChange = { changed ->
            todo.completed = changed
          })
          Text(todo.name)
        }
      }
    }
    // React Native
    // Flutter
    // Compose Desktop  Compose Web 跨平台 / 多平台  Multi-Platform / Cross-platform
  }
}

@Preview(showBackground = true)
@Composable
fun TodosPreview() {
  val todos = listOf(
    Todo().apply {
      name = "吃饭"
    },
    Todo().apply {
      name = "上厕所"
    },
    Todo().apply {
      name = "休息"
    },
  )
  Todos(todos)
}