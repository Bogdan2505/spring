import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Page} from "../model/page";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  public findAll(){
    return this.http.get<Page>('/rest/v1/product/all')
  }
}
