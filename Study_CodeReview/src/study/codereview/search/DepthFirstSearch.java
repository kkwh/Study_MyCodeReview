package study.codereview.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author: caos321
 * @date: 31 October 2021 (Sunday)
 */
public class DepthFirstSearch {

    static class Node {

        private final String name; // 노드의 이름
        private final List<Node> subNodes; // 노드의 하위 노드 목록

        public Node(final String name) {
            this.name = name;
            this.subNodes = new ArrayList<>();
        }

        public Node(final String name, final List<Node> subNodes) {
            this.name = name;
            this.subNodes = subNodes;
        }

        public String getName() {
            return name;
        }

        public List<Node> getSubNodes() {
            return subNodes;
        }
    }

    public static Optional<Node> search(final Node node, final String name) { // 루트 노드와 찾으려는 노드의 이름
        if (node.getName().equals(name)) { // 현재 노드의 이름이 찾으려는 노드의 이름과 일치한다면
            return Optional.of(node); // Optional 객체에 현재 노드를 담아서 반환
        }
        
        // 이름이 일치하지 않는다면 
        return node
            .getSubNodes() // 노드의 하위 노드 목록에 
            .stream()      // 스트림 생성
            .map(value -> search(value, name)) // 각 하위 노드에 대해 search 메서드를 재귀적으로 호출
            .flatMap(Optional::stream) // 각 하위 노드에서 반환된 Optional 값을 스트림으로 묶어서
            .findAny(); // 일치하는 하위 노드 중 임의의 하나를 찾아서 반환
    }

    public static void assertThat(final Object actual, final Object expected) { // 찾은 노드의 이름과 기대(예상)하는 노드의 이름을 비교
        if (!Objects.equals(actual, expected)) {                                // 같지 않으면 AssertionError를 던짐.
            throw new AssertionError(
                String.format("expected=%s but was actual=%s", expected, actual)
            );
        }
    }

    public static void main(final String[] args) {
        final Node rootNode = new Node(
            "A",
            List.of(
                new Node(
                    "B",
                    List.of(
                        new Node("D"),
                        new Node("F", List.of(new Node("H"), new Node("I")))
                    )
                ),
                new Node("C", List.of(new Node("G"))),
                new Node("E")
            )
        );

        {
            final String expected = "I";

            final Node result = search(rootNode, expected)
                .orElseThrow(() -> new AssertionError("Node not found!")); // 결과가 존재하지 않으면 예외를 발생시킴
            
            assertThat(result.getName(), expected);
            
            System.out.println(result.getName());
            for (Node subNode : result.getSubNodes()) {
                System.out.print(subNode.getName() + ", ");
            }
           
        }
        
        System.out.println();
        
        {
            final String expected = "A";

            final Node result = search(rootNode, expected)
                .orElseThrow(() -> new AssertionError("Node not found!"));

            assertThat(result.getName(), expected);
            
            System.out.println(result.getName());
            for (Node subNode : result.getSubNodes()) {
                System.out.print(subNode.getName() + ", ");
            }
        }
        
        System.out.println();
        System.out.println();
        
        {
            final String expected = "F";

            final Node result = search(rootNode, expected)
                .orElseThrow(() -> new AssertionError("Node not found!"));

            assertThat(result.getName(), expected);
            
            System.out.println(result.getName());   
            for (Node subNode : result.getSubNodes()) {
                System.out.print(subNode.getName() + ", ");
            }
        }
        
    }
}