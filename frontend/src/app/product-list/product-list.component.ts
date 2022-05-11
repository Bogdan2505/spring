import { Component, OnInit } from '@angular/core';
import {Product} from "../model/product";
import {ProductService} from "../sercices/product.service";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Product[] = [];

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.productService.findAll().subscribe(res =>{
      this.products = res.content;
    }, err =>{
      console.error(err);
    })
  }

  delete(id: number | null) {

  }
}
