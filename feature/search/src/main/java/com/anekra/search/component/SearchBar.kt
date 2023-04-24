package com.anekra.search.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.anekra.search.screen.SearchScreenEvent
import com.anekra.search.screen.SearchViewModel

@Composable
fun SearchBarContent(
    viewModel: SearchViewModel
) {
    Row(
        modifier = Modifier
            .zIndex(2f)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column {
            CustomSearchBar(
                viewModel = viewModel
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CustomSearchBar(
    viewModel: SearchViewModel
) {
    val state = viewModel.searchState
    val (active, setActive) = remember { mutableStateOf(false) }
    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    
    Box {
        DockedSearchBar(
            query = state.searchQuery,
            onQueryChange = {
                viewModel.onEvent(
                    event = SearchScreenEvent.OnSearchQueryChange(
                        query = it
                    )
                )
            },
            onSearch = {
                setActive(false)
                softwareKeyboardController?.hide()
                viewModel.onEvent(SearchScreenEvent.OnSearch(query = it))
            },
            active = false,
            onActiveChange = {},
            placeholder = { Text(text = "Search Games") },
            leadingIcon = {
                if (!active) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            },
            trailingIcon = {
                if (state.searchQuery.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear Icon",
                        modifier = Modifier.clickable {
                            viewModel.onEvent(
                                event = SearchScreenEvent.OnSearchQueryChange(
                                    query = ""
                                )
                            )
                        })
                }
            },
            colors = SearchBarDefaults.colors(
                containerColor = Color.LightGray.copy(alpha = 0.15f),
                inputFieldColors = TextFieldDefaults.colors()
            ),
            content = {},
        )
        
        BoxWithBorder(
            modifier = Modifier.matchParentSize(),
            borderColor = if (active) Color.White else Color.Transparent,
            borderWidth = 1.dp,
            onClick = { setActive(true) }
        )
        
        BackHandler(enabled = active) {
            setActive(false)
            softwareKeyboardController?.hide()
        }
    }
}

@Composable
fun BoxWithBorder(
    modifier: Modifier = Modifier,
    borderColor: Color,
    borderWidth: Dp,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick)
            .border(
                width = borderWidth,
                color = borderColor,
                shape = RoundedCornerShape(25.dp)
            )
    )
}