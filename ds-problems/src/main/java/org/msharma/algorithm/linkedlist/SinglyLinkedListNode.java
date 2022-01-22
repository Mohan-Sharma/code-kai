package org.msharma.algorithm.linkedlist;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Mohan Sharma Created on 11/06/17.
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of="data")
public class SinglyLinkedListNode
{
    public int data;
    public SinglyLinkedListNode next;

    public SinglyLinkedListNode(int data)
    {
        this.data = data;
    }
}
