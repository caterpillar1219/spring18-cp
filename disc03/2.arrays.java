public static int[] insert(int[] arr, int item, int position){
    arr.size += 1;
    if(position >= arr.size){
        arr[arr.size - 1] = item;
        return
    }
    for (int i = arr.size - 1; i > position; i--){
        arr[i] = arr[i - 1];
    }
    arr[position] = item;
}
