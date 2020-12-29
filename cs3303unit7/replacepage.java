package cs3303unit7;

import java.util.*;
class replacepage
  {
    public static void main(String args[]) {
      boolean flag;
      int f, page=0, ch, pgf=0, n, chn=0, k, pt;
      int pages[];
      //pgf-page fault
      System.out.println("Menu 1.FIFO 2. LRU 3. LFU");
      System.out.println("ENTER YOUR CHOICE: ");
      Scanner in = new Scanner(System.in);
      ch = in.nextInt();
      switch(ch)
      {
           case 1:
        	   pt=0;
                System.out.println("enter no. of buffers (available buffers in the pool): ");
                f=in.nextInt();
                int buffer[]=new int[f];
                for(int i=0;i<f;i++)
                {
                       buffer[i]=-1;
                 }
                 System.out.println("enter the no of pages (items to be stored): ");
                 n=in.nextInt();
                 pages=new int[n];
                 System.out.println("enter the page value (an item to place in a buffer): ");
                 for(int j=0;j<n;j++)
                        pages[j]=in.nextInt();
                 do{
                        int pg=0;
                        for(pg=0;pg<n;pg++)
                        {
                               page=pages[pg];
                                    flag=true;
                                    for(int j=0;j<f;j++)
                                    {
                                          if(page==buffer[j])
                                          {
                                              flag=false;
                                              break;
                                           }
                                      }
                                      if(flag)
                                      {
                                           buffer[pt]=page;
                                           pt++;
                                           if(pt==f) pt=0;
                                           System.out.print("buffer :");
                                           for(int j=0;j<f;j++)
                                                 System.out.print(buffer[j]+" ");
                                           System.out.println();
                                           pgf++;
                                       }
                                       else
                                      {
                                           System.out.print("buffer :");
                                           for(int j=0;j<f;j++)
                                                 System.out.print(buffer[j]+" ");
                                           System.out.println();
                                       }
                                       chn++;
                        }
                  } while(chn!=n);
                  break;
           case 2:
                k=0;
                System.out.println("enter no. of buffers (available buffers in the pool): ");
                f=in.nextInt();
                int buffer1[]=new int[f];
                int a[]=new int[f];
                int b[]=new int[f];
                for(int i=0;i<f;i++)
                {
                       buffer1[i]=-1;
                       a[i]=-1;
                       b[i]=-1;
                 }
                 System.out.println("enter the no of pages (items to be stored): ");
                 n=in.nextInt();
                 pages=new int[n];
                 System.out.println("enter the page value (an item to place in a buffer): ");
                 for(int j=0;j<n;j++)
                       pages[j]=in.nextInt();
                 do{
                       int pg=0;
                       for(pg=0;pg<n;pg++)
                       {
                           page=pages[pg];
                           flag=true;
                           for(int j=0;j<f;j++)
                           {
                                 if(page==buffer1[j])
                                 {
                                     flag=false; break;
                                  }
                            }
                               for(int j=0;j<f && flag;j++)
                            {
                                   if(buffer1[j]==a[f-1])
                                   {
                                      k=j;
                                        break;
                                    }
                             }
                            if(flag)
                            {
                                 buffer1[k]=page;
                                 System.out.println("buffer :" );
                                 for(int j=0;j<f;j++)
                                        System.out.print(buffer1[j]+" ");
                                 pgf++;
                                 System.out.println();
                             }
                             else
                             {
                                 System.out.println("buffer :" );
                                 for(int j=0;j<f;j++)
                                        System.out.print(buffer1[j]+" ");
                                 System.out.println();
                              }
                              int p=1;
                              b[0]=page;
                              for(int j=0;j<a.length;j++)
                              {
                                     if(page!=a[j] && p<f)
                                    {
                                         b[p]=a[j];
                                         p++;
                                     }
                               }
                               for(int j=0;j<f;j++)
                               {
                                     a[j]=b[j];
                                }
                                     chn++;
                                }
                           } while(chn!=n);
                           break;
                    case 3:
                          k=0;
                          pgf=0;
                          int sml;
                          System.out.println("enter no. of buffers (available buffers in the pool): ");
                               f=in.nextInt();
                          int buffer2[]=new int[f];
                          int cnt[]=new int [f];
                          flag=true;
                          for(int i=0;i<f;i++)
                          {
                                buffer2[i]=-1;
                                cnt[i]=0;
                           }
                          System.out.println("enter the page value (an item to place in a buffer): ");
                          n=in.nextInt();
                          pages=new int[n];
                          System.out.println("enter the page value (an item to place in a buffer): ");
                          for(int j=0;j<n;j++)
                                 pages[j]=in.nextInt();
                          do
                          {
                                 int pg=0;
                                 for(pg=0;pg<n;pg++)
                                 {
                                        page=pages[pg];
                                        flag=true;
                                        for(int j=0;j<f;j++)
                                        {
                                               if(page==buffer2[j])
                                               {
                                                      flag=false;
                                                      cnt[j]++;
                                                      break;
                                                }
                                         }
                                         if(flag)
                                        {
                                              sml=cnt[0];
                                              for(int j=0;j<f;j++)
                                              {
                                                     if(cnt[j]<sml)
                                                     {
                                                           sml=cnt[j];
                                                           break;
                                                      }
                                         }
                                         for(int j=0;j<f;j++)
                                        {
                                                if(sml==cnt[j] )
                                                   {
                                                      buffer2[j]=page;
                                                       k=j;
                                                       break;
                                                 }
                                         }
                                         cnt[k]=1;
                                         System.out.print("buffer :");
                                         for(int j=0;j<f;j++)
                                         {
                                               System.out.print(buffer2[j]+" ");
                                               System.out.println();
                                               pgf++;
                                          }
                                    }
                                    else
                                    {
                                         System.out.print("buffer :");
                                         for(int j=0;j<f;j++)
                                               System.out.print(buffer2[j]+" ");
                                         System.out.println();
                                     }
                                    chn++;
                                }
                          } while(chn!=n);
                            break;
                    }
             }
     }