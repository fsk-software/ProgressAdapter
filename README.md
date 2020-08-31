# ProgressAdapter
Android library that provides an adapter that shows a loading screen when no other items exist and an error screen when the error flag is set.  Otherwise,
it shows the loaded items.

It also provides an item decorator that can show a static view when the adapter is empty.

[![](https://jitpack.io/v/fsk-software/ProgressAdapter.svg)](https://jitpack.io/#fsk-software/ProgressAdapter)

To use the library add jitpack to the projects repositories: 
 
   ```gradle
   repositories { 
        maven { url "https://jitpack.io" }
   }
   ```
   
Then add the following the library to your modules gradle file
   ```
   dependencies {
         implementation 'com.github.fsk-software:ProgressAdapter:1.0-alpha1'
   }
   ```  

This example uses the SimpleProgressAdapter, but the ProgressAdapter is very similar.  To use them, justextend the class and implement the abstract methods:
        
 class GroceriesAdapter : SimpleProgressAdapter<String>() {

    private class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView: TextView = itemView.findViewById(R.id.name)
    }

    private class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val progressBar: ProgressBar = itemView.findViewById(R.id.progress)
    }

    override fun onCreateActualViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder =
        ItemViewHolder(inflater.inflate(R.layout.item_grocery_item, parent, false))

    override fun onCreateErrorViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder =
        SimpleViewHolder(inflater.inflate(R.layout.item_error, parent, false))


    override fun onCreateProgressViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): RecyclerView.ViewHolder =
        ProgressViewHolder(inflater.inflate(R.layout.item_progress, parent, false))


    override fun onBindActualViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).apply {
            nameView.text = items[position]
        }
    }

    override fun onBindProgressViewHolder(holder: RecyclerView.ViewHolder, progress: Int) {
        (holder as ProgressViewHolder).apply {
            progressBar.progress = progress
        }
    }

To use the EmptyItemDecorator, just assign a view to it and add it to the recycler view:

    recyclerview.apply {
        addItemDecoration(EmptyItemDecoration().apply {
            this.view = LayoutInflater.from(requireContext()).inflate(R.layout.your_layout, recyclerview, false)
        }) 
    }

License
=======
    Copyright 2020 FSK Consulting, Inc

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
