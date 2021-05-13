# 자료구조, 알고리즘
## 자료구조와 알고리즘 관련 Test코드 까지 작성하여 정리

### 1.Tree
 - 중위 순회(inorder) : 좌 -> root -> 우
 - 전위 순회(preorder) : root -> 좌 -> 우
 - 후위 순회(postorder) : 좌 -> 우 -> root

### 2.MaxHeap, MinHeap
 - add, getSortedList

### 3.GrapWithList, GrapWithMatrix
 - add, addDirection, dfs, bfs

### 4. Trie
 - insert, contains, delete

### 5. Graph에 DijkstraMin 알고리즘 추가
 - 노드들간 최소 가중치가 되도록 경로찾기
 - dijkstraMin(시작노드 index, 찾고자 하는 노드 index)
 - MinMaxGraphResult > 최소가중치와 경로 집합 반환

### 6. Graph에 bellmanMin 알고리즘 추가
 - 음수가 존재하여도 최소 가중치 경로 찾기 가능.
 - bellmanMin(시작노드 index, 찾고자 하는 노드 index)
 - MinMaxGraphResult > 최소가중치와 경로 집합 반환