/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consecutive_Chars;

/**
 * @file veri_yapıları
 * @description Herhangi bir txt dosyasından alınan kelimelerden ardışık
 * karakter bilgilerinin tutulduğu multi singly linked list yapısını oluşturur.
 * @assignment 1. Ödev
 * @date 19/04/2020
 * @author Veli Tayyib Akdeniz // vtakdeniz@gmail.com
 */

/**
 *
 * @author veliakdeniz
 */
public class Node {

    Node nextNode;
    SideNode side;
    char data;
}

class SideNode {

    SideNode nextSide;
    char data;
    int counter;

}
