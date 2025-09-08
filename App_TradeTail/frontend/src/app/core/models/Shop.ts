import { Municipality } from './Municipality';
import { User } from './User';

export interface Shop {
  id: number;
  image: string;
  name: string;
  description: string;
  t_contact: string[];
  address: string;
  latitude: number;
  longitude: number;
  stars: number;

  category: Category | null;
  users: User[];
  municipality: Municipality | null;
}

export interface Category {
  id: number;
  name: string;
  sub_categories: string[];
  avatar: string;
}
