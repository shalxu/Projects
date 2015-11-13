//
//  LList.h
//  Lab09_PardeeXu
//
//  Created by Hanna on 2/3/15.
//  Copyright (c) 2015 University of Denver. All rights reserved.
//
#include <iostream>
#ifndef Lab09_PardeeXu_LList_h
#define Lab09_PardeeXu_LList_h

template <typename newType> struct ListItem {
    newType item;
    ListItem* next;
};

template <typename newType> class LList {
private:
    ListItem<newType> *head;

public:
    
    LList()
    {
        head=nullptr;
    }
    
    ~LList(){
        delete head;
    }
    
    bool IsEmpty()
    {
        if(head == nullptr){
            return true;
        }
        else
            return false;
    }
    
    void AddFront(newType item){
            ListItem<newType> *temp =new ListItem<newType>;
            temp->item = item;
            temp->next = head;
            head=temp;
    };
    
    newType PeekFront(){
        return head->item;
    }
    void RemoveFront(){
        if(head != nullptr){
            head=head->next;
        }
    }
    void Print(){
        ListItem<newType> *temp = head;
        while(temp != nullptr)
        {
            std::cout <<temp->item<<" ";
            temp=temp->next;
        }
        std::cout<<std::endl;
    }
    
    
};

#endif
