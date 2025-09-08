import { Municipality } from './Municipality';

export interface User {
  id: number;
  avatar: string;
  email: string;
  first_name: string;
  is_active: boolean;
  last_name: string;
  name: string;
  password: string;
  role: string | null;
  telephone_number: string;
  token: string;
  username: string;
  municipality_id: Municipality | null;
  active: boolean;
}

export interface Auth {
  email: string;
  password: string;
}
