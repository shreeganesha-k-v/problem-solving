def find_min(arr:list)->int:
    low = 0
    high = len(arr)-1
    
    while low <= high:
        mid = low + (high - low)//2
        
        if arr[mid] > arr[high]:
            low = mid + 1
        else:
            high = mid 
    return arr[low]
    
arr = [7,1,5,3,6,4]
#print(find_profit(arr))
print(find_min([4,5,6,7,0,1,2,3]))